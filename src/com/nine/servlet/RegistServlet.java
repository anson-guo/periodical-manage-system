package com.nine.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nine.Dao.ReaderDao;

public class RegistServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

//	@Override
//	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		req.setCharacterEncoding("utf-8");
//		String username = req.getParameter("username");
//		String password = req.getParameter("password");
//		String rpsw = req.getParameter("rpsw");
//		System.out.println(username + " " + password + " " + rpsw);
//		if(username == null || username.trim().isEmpty()) {
//			req.setAttribute("msg", "账号不能为空");
//			req.getRequestDispatcher("/regist.jsp").forward(req, resp);
//			return;
//		}
//		if(password == null || password.trim().isEmpty()) {
//			req.setAttribute("msg", "密码不能为空");
//			req.getRequestDispatcher("/regist.jsp").forward(req, resp);
//			return;
//		}
//		if(!password.equals(rpsw)) {
//			req.setAttribute("msg", "两次密码不同");
//			req.getRequestDispatcher("/regist.jsp").forward(req, resp);
//			return;
//		}
//		UserDao u = new UserDao();
//		u.addUser(username, password);
//		req.setAttribute("msg", "恭喜："+username+",注册成功");
//		try {
//			Thread.sleep(1000);
//			} catch (InterruptedException e) {
//			e.printStackTrace(); 
//			}
//		req.getRequestDispatcher("/index.jsp").forward(req, resp);
//	}
//	
}
