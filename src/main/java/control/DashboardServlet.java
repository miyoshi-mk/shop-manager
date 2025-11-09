package control;

import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.Order;
import model.Product;
import model.dao.OrderDAO;
import model.dao.ProductDAO;

/**
 * 集計用サーブレット
 * 　ダッシュボード（商品数、在庫数、発注件数の可視化）
 */
@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProductDAO productDAO = new ProductDAO();
	private OrderDAO orderDAO = new OrderDAO();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			List<Product> products = productDAO.selectAll();
			List<Order> orders = orderDAO.selectAll();

			int totalProducts = products.size();
			int totalStock = products.stream().mapToInt(Product::getStock).sum();
			int totalOrders = orders.size();

			// --- 商品ID → Product のマップ ---
			Map<Integer, Product> productMap = products.stream()
					.collect(Collectors.toMap(Product::getProductId, p -> p));

			// --- 売上集計 ---
			int totalSales = 0;
			Map<Integer, Integer> salesMap = new LinkedHashMap<>(); // 商品別売上
			Map<YearMonth, Integer> monthlySales = new LinkedHashMap<>(); // 月別売上

			for (Order o : orders) {
				Product p = productMap.get(o.getProductId());
				if (p != null) {
					int sale = (int) (p.getPrice() * o.getQuantity());
					totalSales += sale;

					// 商品別売上
					salesMap.put(o.getProductId(), salesMap.getOrDefault(o.getProductId(), 0) + sale);

					// 月別売上
					if (o.getOrderDate() != null) {
						LocalDate date = o.getOrderDate().toInstant()
								.atZone(ZoneId.systemDefault())
								.toLocalDate();
						YearMonth ym = YearMonth.from(date);
						monthlySales.put(ym, monthlySales.getOrDefault(ym, 0) + sale);
					}
				}
			}

			// --- Chart.js 用にデータを整形 ---
			String productLabelsJson = "[" + products.stream()
					.map(p -> "\"" + p.getProductName() + "\"")
					.collect(Collectors.joining(",")) + "]";
			String productSalesJson = "[" + products.stream()
					.map(p -> String.valueOf(salesMap.getOrDefault(p.getProductId(), 0)))
					.collect(Collectors.joining(",")) + "]";

			String monthLabelsJson = "[" + monthlySales.keySet().stream()
					.map(ym -> "\"" + ym.toString() + "\"")
					.collect(Collectors.joining(",")) + "]";
			String monthValuesJson = "[" + monthlySales.values().stream()
					.map(String::valueOf)
					.collect(Collectors.joining(",")) + "]";

			// --- JSPへ渡す ---
			request.setAttribute("totalProducts", totalProducts);
			request.setAttribute("totalStock", totalStock);
			request.setAttribute("totalOrders", totalOrders);
			request.setAttribute("totalSales", totalSales);
			request.setAttribute("productLabelsJson", productLabelsJson);
			request.setAttribute("productSalesJson", productSalesJson);
			request.setAttribute("monthLabelsJson", monthLabelsJson);
			request.setAttribute("monthValuesJson", monthValuesJson);

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
