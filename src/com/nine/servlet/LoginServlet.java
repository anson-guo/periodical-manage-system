package com.nine.servlet;

import java.io.*;


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
//		req.setCharacterEncoding("UTF-8"); 
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
//			System.out.println("login sb'length is "+sb.length());
			if (sb.length() > 0) {
					jsonin = JSONObject.fromObject(sb.toString());
//					System.out.println("message is "+jsonin.getString("message"));
					String idnumber = jsonin.getString("username");
					String password = jsonin.getString("password");
					System.out.println(idnumber.length());
					if(idnumber.length()==10) {
						
//						System.out.println("reader");
						jsonout.put("istrue", ud.findUser(idnumber,password));
					}else if(idnumber.length()==8) {
//						System.out.println("manager");
						jsonout.put("istrue", md.findManager(idnumber, password));
					}else {
						jsonout.put("istrue", false);
					}
					System.out.println(jsonout.toString());
					resp.getWriter().write(jsonout.toString());
					
			}
			
		}if(action.equals("success.php")) {
			String ID = req.getParameter("username");
			req.getSession().setAttribute("login", true);
			req.getSession().setAttribute("ID", ID);
//			System.out.println("ID is "+ID);
			if(ID.length()==10) {
				resp.sendRedirect("../user_index.html");
			}else if(ID.length()==8) {
				String post = md.findPost(ID);
//				System.out.println(post);
				if(post.equals("老板")) {
					resp.sendRedirect("../boss_index.html");
				}else if(post.equals("管理员")) {
					resp.sendRedirect("../manager_index.html");
				}
			}
		}
	}
}