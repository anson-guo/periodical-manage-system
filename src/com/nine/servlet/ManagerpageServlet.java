package com.nine.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ManagerpageServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8"); 
		resp.setContentType("text/html;charset=utf-8");
		resp.setHeader("Cache-Control", "no-cache");
		String action = req.getRequestURI().split("/")[3];
		System.out.println("manager action is " + action);

		String readerID =(String) req.getSession().getAttribute("readerID");
		//加载用户信息
		if(action.equals("mangerinfo.php")) {
			
		}
		
	}
	
}
