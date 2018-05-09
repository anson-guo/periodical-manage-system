package com.nine.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nine.Dao.UserDao;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class UserpageServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8"); 
		resp.setContentType("text/html;charset=utf-8");
		resp.setHeader("Cache-Control", "no-cache");
		System.out.println("start UserpageServlet");
		UserDao ud = new UserDao();
		String readerID =(String) req.getSession().getAttribute("readerID");
		try {
			JSONObject reader = ud.getReader(readerID);
//			System.out.println(reader.toString());	
			resp.getWriter().write(reader.toString());
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

}
