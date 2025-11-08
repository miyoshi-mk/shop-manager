package control;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.User;
import model.dao.UserDAO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO = new UserDAO();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 //  登録直後のリダイレクトかチェック
	    String registered = request.getParameter("registered");
	    if ("true".equals(registered)) {
	        request.setAttribute("message", "ユーザー登録が完了しました。ログインしてください。");
	    }

	    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
	    dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");

		try {
            User user = userDAO.login(userName, password);
            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("userName", user.getUserName());
                response.sendRedirect(request.getContextPath() + "/dashboard");
                return;
            } else {
                request.setAttribute("error", "ユーザー名またはパスワードが違います");
                request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();

            // ユーザ向けメッセージをセットしてログイン画面へ戻す
            request.setAttribute("error", "ログイン処理中にエラーが発生しました（詳細はコンソールを確認）");
            request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
        }
    }
}
