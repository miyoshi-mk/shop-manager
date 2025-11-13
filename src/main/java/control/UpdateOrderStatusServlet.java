package control;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.Order;
import model.dao.OrderDAO;
import model.dao.ProductDAO;

/**
 * Servlet implementation class UpdateOrderStatusServlet
 */
@WebServlet("/updateOrderStatus")
public class UpdateOrderStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderDAO orderDao = new OrderDAO();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateOrderStatusServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

	        try {
	            int orderId = Integer.parseInt(request.getParameter("id"));
	            String status = request.getParameter("status");

	            // ステータスを更新
	            orderDao.updateStatus(orderId, status);

	         // ステータスが「入荷済」なら在庫を加算
	            if ("入荷済".equals(status)) {
	                Order order = orderDao.selectById(orderId);  // 発注情報を取得
	                ProductDAO productDao = new ProductDAO();
	                productDao.updateStock(order.getProductId(), order.getQuantity());  // 在庫を加算
	            }

	            // 発注一覧へリダイレクト
	            response.sendRedirect(request.getContextPath() + "/page?name=orderList");
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
