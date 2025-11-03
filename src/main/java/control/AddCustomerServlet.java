package control;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.Customer;
import model.dao.CustomerDAO;

/**
 * Servlet implementation class AddCustomerServlet
 */
@WebServlet("/add-customer")
public class AddCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCustomerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 request.getRequestDispatcher("/WEB-INF/jsp/addCustomer.jsp").forward(request, response);
    }

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");

        try {
			CustomerDAO dao = new CustomerDAO();
			Customer c = new Customer(0, name, email, phone, address);
			dao.insert(c);
			response.sendRedirect(request.getContextPath() + "/customerList");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "登録中にエラーが発生しました");
            request.getRequestDispatcher("/WEB-INF/jsp/addCustomer.jsp").forward(request, response);
        }
    }

}
