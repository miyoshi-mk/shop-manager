package control;

import java.io.IOException;
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	try {
            List<Product> products = productDAO.selectAll();
            List<Order> orders = orderDAO.selectAll();

            int totalProducts = products.size();
            int totalStock = products.stream().mapToInt(Product::getStock).sum();
            int totalOrders = orders.size();

         // --- 売上合計を算出 ---
            Map<Integer, Product> productMap = products.stream()
                    .collect(Collectors.toMap(Product::getProductId, p -> p));

            int totalSales = 0;
            for (Order o : orders) {
                Product p = productMap.get(o.getProductId());
                if (p != null) {
                    totalSales += p.getPrice() * o.getQuantity();
                }
            }
            
            request.setAttribute("totalProducts", totalProducts);
            request.setAttribute("totalStock", totalStock);
            request.setAttribute("totalOrders", totalOrders);
            request.setAttribute("totalSales", totalSales);

            // JSPへフォワード
            request.getRequestDispatcher("/WEB-INF/jsp/dashboard.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
