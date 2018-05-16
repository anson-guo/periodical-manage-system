package com.nine.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nine.dao.ManagerDao;
import com.nine.dao.ReaderDao;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class ManagerpageServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8"); 
		resp.setContentType("text/html;charset=utf-8");
		resp.setHeader("Cache-Control", "no-cache");
		String action = req.getRequestURI().split("/")[3];
		System.out.println("manager action is " + action);
		String ManagerID =(String) req.getSession().getAttribute("ID");
		ManagerDao md = new ManagerDao();
		ReaderDao rd = new ReaderDao();
		//加载用户信息
		if(action.equals("managerinfo.php")) {
			try {
				System.out.println("ManagerID is "+ManagerID);
				JSONObject reader = md.getEmployee(ManagerID);
				resp.getWriter().write(reader.toString());
				return;
			} catch(IOException e) {
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
//				System.out.println(sb.toString());
				jsonin = JSONObject.fromObject(sb.toString());
				System.out.println("message is "+jsonin.getString("message"));
				if(jsonin.getString("message").equals("addreader")) {
					//添加读者
					jsonout = new JSONObject();
//					System.out.println("jsonin :"+jsonin.toString());
					jsonout.put("istrue", rd.addReader(jsonin.getString("reader_id"), jsonin.getString("reader_name"),jsonin.getString("department"),jsonin.getString("sex")));
					resp.getWriter().write(jsonout.toString());
					return;
				}else if(jsonin.getString("message").equals("reader_id_validate")) {
					//验证读者id
					jsonout = new JSONObject();
					if(rd.findUser(jsonin.getString("reader_id"))!=null) {
						jsonout.put("istrue", "true");
					}
					else {
						jsonout.put("istrue", "false");
					}
					resp.getWriter().write(jsonout.toString());
					return;
				}else if(jsonin.getString("message").equals("search_reader")) {
					//查找读者
					JSONArray jsonArray = rd.searchReader(jsonin.getString("key"), jsonin.getString("search_item"));
					resp.getWriter().write(jsonArray.toString());
				}else if(jsonin.getString("message").equals("edit_reader")) {
					//编辑读者信息
					jsonout = new JSONObject();
					jsonout.put("istrue", rd.modifyReader(jsonin.getString("readerID"), jsonin.getString("readerName"), jsonin.getString("department"), jsonin.getString("reputation"),jsonin.getString("sex")));
					resp.getWriter().write(jsonout.toString());
				}
				else if(jsonin.getString("message").equals("del_reader")) {
					//删除读者
					jsonout = new JSONObject();
					jsonout.put("istrue", rd.delReader(jsonin.getString("readerID")));
					resp.getWriter().write(jsonout.toString());
				}
			}
		}
	}
	
}
