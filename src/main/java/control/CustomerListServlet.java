package control;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.Customer;
import model.dao.CustomerDAO;

/**
 * Servlet implementation class CustomerListServlet
 */
@WebServlet("/customerList")
public class CustomerListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CustomerDAO customerDAO = new CustomerDAO();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			CustomerDAO dao = new CustomerDAO();
			List<Customer> list = dao.selectAll();
			System.out.println("customer list size = " + (list == null ? 0 : list.size()));

			request.setAttribute("customerList", list);
			request.getRequestDispatcher("/WEB-INF/jsp/customerList.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();

			request.setAttribute("error", "顧客一覧の取得中にエラーが発生しました");
			request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
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
