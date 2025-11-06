package control;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
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
 * Servlet implementation class AddOrderServlet
 */
@WebServlet("/addOrder")
public class AddOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderDAO orderDao = new OrderDAO();
    private ProductDAO productDao = new ProductDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddOrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
            // 商品一覧を取得してJSPへ渡す
            List<Product> products = productDao.selectAll();
            request.setAttribute("products", products);
        } catch (Exception e) {
            e.printStackTrace();
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/addOrder.jsp");
        dispatcher.forward(request, response);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

        int productId = Integer.parseInt(request.getParameter("productId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String status = request.getParameter("status");

        Order o = new Order();
        o.setProductId(productId);
        o.setQuantity(quantity);
        o.setStatus(status);

        try {
            orderDao.insert(o);
            request.setAttribute("message", "発注を登録しました。");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "登録に失敗しました。");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/addOrder.jsp");
        dispatcher.forward(request, response);
    }

}
