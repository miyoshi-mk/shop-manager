package control;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Order;
import model.Product;
import model.Sales;
import model.dao.OrderDAO;
import model.dao.ProductDAO;
import model.dao.SalesDAO;

/**
 * 集計用サーブレット
 * 　ダッシュボード（商品数、在庫数、発注件数の可視化）
 */
@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProductDAO productDAO = new ProductDAO();
	private SalesDAO salesDAO = new SalesDAO();
	private OrderDAO orderDAO = new OrderDAO();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			List<Product> products = productDAO.selectAll();
			List<Sales> salesList = salesDAO.selectAll();
			List<Order> orders = orderDAO.selectAll();

			int totalProducts = products.size();
			int totalStock = products.stream().mapToInt(Product::getStock).sum();
			int totalOrders = orders.size();
			int totalSalesCount = salesList.size();
			int totalSales = 0;

			Map<Integer, Product> productMap = products.stream()
					.collect(Collectors.toMap(Product::getProductId, p -> p));


			// --- 売上集計 ---
			Map<Integer, Integer> salesMap = new LinkedHashMap<>();
			Map<String, Integer> weeklySales = new LinkedHashMap<>();

			for (Sales s : salesList) {
			    Product p = productMap.get(s.getProductId());
			    if (p != null && s.getSaleDate() != null) {
			        int sale = (int) (p.getPrice() * s.getQuantity());
			        totalSales += sale;

			        // 商品別売上
			        salesMap.put(s.getProductId(), salesMap.getOrDefault(s.getProductId(), 0) + sale);

			        // 週別売上
			        LocalDate date = s.getSaleDate().toLocalDate();
			        WeekFields wf = WeekFields.of(Locale.getDefault());
			        int weekNumber = date.get(wf.weekOfWeekBasedYear());
			        String yearWeek = date.getYear() + "-W" + String.format("%02d", weekNumber);
			        weeklySales.put(yearWeek, weeklySales.getOrDefault(yearWeek, 0) + sale);
			    }
			}

			// --- Chart.js 用にデータを整形 ---
			List<Product> soldProducts = products.stream()
					.filter(p -> salesMap.getOrDefault(p.getProductId(), 0) > 0)
					.collect(Collectors.toList());

			String productLabelsJson = "[" + soldProducts.stream()
					.map(p -> "\"" + p.getProductName() + "\"")
					.collect(Collectors.joining(",")) + "]";
			String productSalesJson = "[" + soldProducts.stream()
					.map(p -> String.valueOf(salesMap.getOrDefault(p.getProductId(), 0)))
					.collect(Collectors.joining(",")) + "]";

			String weekLabelsJson = "[" + weeklySales.keySet().stream()
					.map(w -> "\"" + w + "\"")
					.collect(Collectors.joining(",")) + "]";
			String weekValuesJson = "[" + weeklySales.values().stream()
					.map(String::valueOf)
					.collect(Collectors.joining(",")) + "]";

			// --- JSPへ渡す ---
			request.setAttribute("totalProducts", totalProducts);
			request.setAttribute("totalStock", totalStock);
			request.setAttribute("totalOrders", totalOrders);
			request.setAttribute("totalSales", totalSales);
			request.setAttribute("totalSalesCount", totalSalesCount);
			request.setAttribute("productLabelsJson", productLabelsJson);
			request.setAttribute("productSalesJson", productSalesJson);
			request.setAttribute("weekLabelsJson", weekLabelsJson);
			request.setAttribute("weekValuesJson", weekValuesJson);

			request.getRequestDispatcher("/WEB-INF/jsp/dashboard.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
