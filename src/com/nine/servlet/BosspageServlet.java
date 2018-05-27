package com.nine.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nine.dao.BossDao;
import com.nine.dao.ManagerDao;
import com.nine.dao.PeriodicalDao;
import com.nine.dao.ReaderDao;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class BosspageServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8"); 
		resp.setContentType("text/html;charset=utf-8");
		resp.setHeader("Cache-Control", "no-cache");
		String action = req.getRequestURI().split("/")[3];
		System.out.println("--------------------------------- ");
		System.out.println("manager action is " + action);
		String BossID =(String) req.getSession().getAttribute("ID");
		ManagerDao md = new ManagerDao();
		ReaderDao rd = new ReaderDao();
		PeriodicalDao perd = new PeriodicalDao();
		BossDao bossd = new BossDao();
		JSONObject jsonout = new JSONObject();
		//加载用户信息
		if(action.equals("bossinfo.php")) {
			jsonout.put("managerCount", bossd.managerCount());
			jsonout.put("readerCount", bossd.readerCount());
			jsonout.put("periodicalCount", bossd.periodicalCount());
			jsonout.put("sexCount", bossd.sexCount());
			jsonout.put("departmentCount", bossd.departmentCount());
			resp.getWriter().write(jsonout.toString());
		}
		else if(action.equals("employeeinfo.php")) {
			JSONArray jsonArray = null;
			jsonArray = md.getAllEmployee();
//			System.out.println(jsonArray.toString());
			resp.getWriter().write(jsonArray.toString());
			
		}
		//有数据传输的功能
		else if(action.equals("sendmessage.php")) {
			//获取前端的数据
			BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream(),"utf-8"));
			String line = null;
			StringBuilder sb = new StringBuilder();
			JSONObject jsonin = null;
			while((line = br.readLine()) != null){
				sb.append(line);
			}
			if (sb.length() > 0) {
//				System.out.println(sb.toString());
				jsonin = JSONObject.fromObject(sb.toString());
				System.out.println("message is "+jsonin.getString("message"));
				if(jsonin.getString("message").equals("editEmployee")) {
					jsonout.put("istrue", bossd.modifyEmployee(jsonin.getString("employeeName"), jsonin.getString("employeeID")));
					resp.getWriter().write(jsonout.toString());
					return;
				}else if(jsonin.getString("message").equals("validateEmployeeID")) {
					jsonout.put("istrue", bossd.validateEmployee(jsonin.getString("employeeID")));
					resp.getWriter().write(jsonout.toString());
					return;
					
				}else if(jsonin.getString("message").equals("addEmployeeID")) {
					jsonout.put("istrue", bossd.addEmployee(jsonin.getString("employeeID"), jsonin.getString("employeeName"),jsonin.getString("sex")));
					resp.getWriter().write(jsonout.toString());
					return;
					
				}else if(jsonin.getString("message").equals("showWorkload")) {
					JSONArray jsonArray = null;
					jsonArray = md.returnWorkload(jsonin.getString("employeeID"));
					resp.getWriter().write(jsonArray.toString());
				}else if(jsonin.getString("message").equals("deleteEmployee")) {
					jsonout.put("istrue", bossd.deleteEmployee(jsonin.getString("employeeID")));
//					System.out.println(jsonout.toString());
					resp.getWriter().write(jsonout.toString());
				}
			}
		}
	}

	
}
