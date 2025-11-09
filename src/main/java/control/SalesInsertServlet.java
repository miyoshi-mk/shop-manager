package control;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.Sales;
import model.dao.SalesDAO;

/**
 * Servlet implementation class SalesInsertServlet
 */
@WebServlet("/sales-insert")
public class SalesInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SalesInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/salesInsert.jsp");
	        dispatcher.forward(request, response);
	    }

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");

            int productId = Integer.parseInt(request.getParameter("product_id"));
            int customerId = Integer.parseInt(request.getParameter("customer_id"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            String remarks = request.getParameter("remarks");

            Sales s = new Sales();
            s.setProductId(productId);
            s.setCustomerId(customerId);
            s.setQuantity(quantity);
            s.setRemarks(remarks);

            try {
            	SalesDAO dao = new SalesDAO();
            	dao.insertSale(s);
            	 request.setAttribute("message", "売上を登録しました。");
            } catch (Exception e) {
            	e.printStackTrace();
            	request.setAttribute("error", "売上登録中にエラーが発生しました。");
            }
            
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/salesInsert.jsp");
            rd.forward(request, response);
    }

}
