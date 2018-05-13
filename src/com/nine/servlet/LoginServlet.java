package com.nine.servlet;

import java.io.*;

import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nine.dao.ReaderDao;

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
		ReaderDao ud = new ReaderDao();
		req.setCharacterEncoding("utf-8");
		String idnumber = req.getParameter("idnumber");
		String password = req.getParameter("password");
//		System.out.println(username);
		String pwd = ud.findUser(idnumber);
		if (pwd == null ) {
			req.setAttribute("readerID", idnumber);
			req.setAttribute("message", "用户不存在");
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		}
		else if (!pwd.equals(password)) {
			req.setAttribute("readerID", idnumber);
			req.setAttribute("message", "密码不正确");
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		}
		else {
//			System.out.println("user is ok");
			req.getSession().setAttribute("readerID", idnumber);
			System.out.println(req.getSession().getAttribute("readerID")+" end login");
			resp.sendRedirect("user_index.html");
		}
			
	}
}