package hong.guo.loginServlet;

import java.io.*;

import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hong.guo.loginDao.UserDao;
import net.sf.json.JSONObject;

public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserDao ud = new UserDao();
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String pwd = ud.findUser(username);
		if (pwd == null ) {
			req.setAttribute("username", username);
			req.setAttribute("message", "用户不存在");
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		}
		else if (!pwd.equals(password)) {
			req.setAttribute("username", username);
			req.setAttribute("message", "密码不正确");
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		}
		else {
			req.getRequestDispatcher("index.html").forward(req, resp);
		}
			
	}
}