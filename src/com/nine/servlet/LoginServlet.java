package com.nine.servlet;

import java.io.*;

import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nine.dao.ManagerDao;
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
		ManagerDao md = new ManagerDao();
		req.setCharacterEncoding("UTF-8"); 
		resp.setContentType("text/html;charset=utf-8");
		resp.setHeader("Cache-Control", "no-cache");
		String action = req.getRequestURI().split("/")[3];
		System.out.println("login action is " + action);
		if(action.equals("validate.php")) {
			BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream(),"utf-8"));
			String line = null;
			StringBuilder sb = new StringBuilder();
			JSONObject jsonin = null;
			JSONObject jsonout = new JSONObject();
			while((line = br.readLine()) != null){
				sb.append(line);
			}
			if (sb.length() > 0) {
					jsonin = JSONObject.fromObject(sb.toString());
//					System.out.println("message is "+jsonin.getString("message"));
					String idnumber = jsonin.getString("username");
					String password = jsonin.getString("password");
					System.out.println(idnumber.length());
					if(idnumber.length()==10) {
						
						System.out.println("reader");
						String pwd = ud.findUser(idnumber);
						if (pwd == null || !pwd.equals(password)) {
							jsonout.put("istrue", false);
							resp.getWriter().write(jsonout.toString());
						}
						else {
							jsonout.put("istrue", true);
							resp.getWriter().write(jsonout.toString());
						}
					}else if(idnumber.length()==8) {
						System.out.println("manager");
						String pwd = md.findManager(idnumber);
						if (pwd == null || !pwd.equals(password)) {
							jsonout.put("istrue", false);
							resp.getWriter().write(jsonout.toString());
						}
						else {
							jsonout.put("istrue", true);
							resp.getWriter().write(jsonout.toString());
						}
					}
			}
			
		}if(action.equals("success.php")) {
			String iD = req.getParameter("username");
			req.getSession().setAttribute("login", true);
			req.getSession().setAttribute("ID", iD);
			if(iD.length()==10) {
				
				resp.sendRedirect("../user_index.html");
			}else if(iD.length()==8) {
				resp.sendRedirect("../manager_index.html");
			}
		}
	}
}