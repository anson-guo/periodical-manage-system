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
		String action = req.getRequestURI().split("/")[3];
		System.out.println("action is " + action);
		ReaderDao rd = new ReaderDao();
		BorrowDao bd = new BorrowDao();
		String readerID =(String) req.getSession().getAttribute("readerID");
		//加载用户信息
		if(action.equals("userinfo.php")) {
//			System.out.println("start userinfo");
			try {
				System.out.println("readerID is "+readerID);
				JSONObject reader = rd.getReader(readerID);
				resp.getWriter().write(reader.toString());
				return;
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		//加载借阅信息
		else if(action.equals("borrowinfo.php")) {
//			System.out.println("start borrowinfo");
			try {
				JSONArray borrowinfo = bd.getBorrowList(readerID);
//				System.out.println(borrowinfo.toString());
				
				resp.getWriter().write(borrowinfo.toString());
				return;
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		//加载历史记录
		else if(action.equals("borrow_history.php")) {
			try {
				JSONArray jsonArray = bd.historylist(readerID);
//						System.out.println(jsonArray.toString());
				resp.getWriter().write(jsonArray.toString());
			}catch(IOException e) {
				e.printStackTrace();
			}

		}
		//有数据传输的功能
		else if(action.equals("sendmessage.php")) {
			//获取前端的数据
			BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream(),"utf-8"));
			String line = null;
			StringBuilder sb = new StringBuilder();
			JSONObject jsonin = null;
			JSONObject jsonout = null;
			while((line = br.readLine()) != null){
				sb.append(line);
			}
			if (sb.length() > 0) {
				jsonin = JSONObject.fromObject(sb.toString());
//				System.out.println(sb.toString());
//				System.out.println(jsonin);
				System.out.println("message is "+jsonin.getString("message"));
				//归还图书
				if(jsonin.getString("message").equals("return_periodical")) {
//					System.out.println(jsonin.getString("periodicalID"));
//					System.out.println((String)req.getSession().getAttribute("readerID"));
					jsonout = new JSONObject();
					jsonout.put("istrue", bd.returnPeriodical(readerID, jsonin.getString("periodicalID")));
					resp.getWriter().write(jsonout.toString());
					return;
				}
				//查询功能
				else if(jsonin.getString("message").equals("search")) {
					
					jsonout = bd.searchlist(jsonin.getString("key"), jsonin.getString("search_item"),jsonin.getString("page"), readerID);
					System.out.println(jsonout.toString());
					resp.getWriter().write(jsonout.toString());
					return ;
				}
				//借阅功能
				if(jsonin.getString("message").equals("borrow_periodical")) {
//					System.out.println("borrow_periodical :"+jsonin.toString());
					jsonout = new JSONObject();
					jsonout.put("istrue",bd.borrowPeriodical(readerID, jsonin.getString("periodicalID")));
					resp.getWriter().write(jsonout.toString());
					return ;
				}
				//验证密码
				if(jsonin.getString("message").equals("validatePassword")) {
					jsonout = new JSONObject();
					jsonout.put("istrue", rd.validatePassword(readerID, jsonin.getString("old_psd")));
					System.out.println(jsonout.toString());
					resp.getWriter().write(jsonout.toString());
				}
				//修改秘密
				if(jsonin.getString("message").equals("modifyPassword")) {
					jsonout = new JSONObject();
					
					System.out.println(jsonin.toString());
					jsonout.put("istrue", rd.modifyPassword(readerID, jsonin.getString("new_psd")));
					System.out.println(jsonout.toString());
					resp.getWriter().write(jsonout.toString());
				}
			}else {
				return ;
			}
		}
		
	}
}
