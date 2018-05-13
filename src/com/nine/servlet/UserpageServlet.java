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

import com.nine.dao.BorrowDao;
import com.nine.dao.ReaderDao;

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
		String action = req.getServletPath();
		System.out.println("action is " + action);
		if(action.equals("/userinfo.php")) {
			System.out.println("start userinfo");
			ReaderDao ud = new ReaderDao();
			String readerID =(String) req.getSession().getAttribute("readerID");
			try {
				JSONObject reader = ud.getReader(readerID);
				resp.getWriter().write(reader.toString());
				return;
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		if(action.equals("/borrowinfo.php")) {
			System.out.println("start borrowinfo");
			BorrowDao bd = new BorrowDao();
			String readerID =(String) req.getSession().getAttribute("readerID");
			try {
				JSONArray borrowinfo = bd.getBorrowList(readerID);
				System.out.println(borrowinfo.toString());
				resp.getWriter().write(borrowinfo.toString());
				return;
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}
