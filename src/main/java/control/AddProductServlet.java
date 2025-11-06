package control;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.Product;
import model.dao.ProductDAO;

/**
 * Servlet implementation class AddProductServlet
 */
@WebServlet("/addProduct")
public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductDAO dao = new ProductDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/addProduct.jsp");
	        dispatcher.forward(request, response);
	    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

        String name = request.getParameter("name");
        int stock = Integer.parseInt(request.getParameter("stock"));
        int price = Integer.parseInt(request.getParameter("price"));

        Product p = new Product();
        p.setProductName(name);
        p.setStock(stock);
        p.setPrice(price);

        try {
            dao.insert(p);
            request.setAttribute("message", "商品を登録しました。");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "登録に失敗しました。");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/addProduct.jsp");
        dispatcher.forward(request, response);
    }
}
