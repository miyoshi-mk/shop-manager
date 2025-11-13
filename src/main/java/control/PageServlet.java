package control;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Customer;
import model.Order;
import model.Product;
import model.Sales;
import model.dao.CustomerDAO;
import model.dao.OrderDAO;
import model.dao.ProductDAO;
import model.dao.SalesDAO;

//画面遷移を一元管理するサーブレット
@WebServlet("/page")
public class PageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final Map<String, Consumer<HttpServletRequest>> handlers = new HashMap<>();

	@Override
	public void init() throws ServletException {
        super.init();
        handlers.put("customerList", req -> {
            try {
                CustomerDAO dao = new CustomerDAO();
                List<Customer> list = dao.selectAll();
                req.setAttribute("customerList", list);
            } catch (Exception e) {
                e.printStackTrace();
                req.setAttribute("error", "顧客一覧の取得中にエラーが発生しました");
            }
        });

        handlers.put("productList", req -> {
            try {
                 ProductDAO dao = new ProductDAO();
                 List<Product> plist = dao.selectAll();
                 req.setAttribute("productList", plist);
            } catch (Exception e) {
                e.printStackTrace();
                req.setAttribute("error", "商品一覧の取得中にエラーが発生しました");
            }
        });

        handlers.put("orderList", req -> {
            try {
                 OrderDAO dao = new OrderDAO();
                 List<Order> olist = dao.selectAll();
                 req.setAttribute("orderList", olist);
            } catch (Exception e) {
                e.printStackTrace();
                req.setAttribute("error", "発注一覧の取得中にエラーが発生しました");
            }
        });

        handlers.put("salesList", req -> {
            try {
                 SalesDAO dao = new SalesDAO();
                 List<Sales> slist = dao.selectAll();
                 req.setAttribute("salesList", slist);
            } catch (Exception e) {
                e.printStackTrace();
                req.setAttribute("error", "発注一覧の取得中にエラーが発生しました");
            }
        });

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String name = request.getParameter("name");
		if (name == null) {
		    name = "dashboard";
		}
		String path = "/WEB-INF/jsp/";

		switch (name) {
		case "productList":
			path += "productList.jsp";
			break;
		case "orderList":
			path += "orderList.jsp";
			break;
		case "customerList":
			path += "customerList.jsp";
			break;
		case "salesList":
			path += "salesList.jsp";
			break;
		default:
			path += "dashboard.jsp";
		}

		Consumer<HttpServletRequest> handler = handlers.get(name);
        if (handler != null) {
            handler.accept(request);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
