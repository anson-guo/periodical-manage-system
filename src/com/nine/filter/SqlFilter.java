package com.nine.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Iterator;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;


public class SqlFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public SqlFilter() {
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("this is sqlFilter");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		// 获得所有请求参数名
//		BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream(),"utf-8"));
//		String line = null;
//		StringBuilder sb = new StringBuilder();
//		JSONObject jsonin = null;
//		while((line = br.readLine()) != null){
//			sb.append(line);
//		}
//		System.out.println("sql sb'length is "+sb.length());
//		if (sb.length() > 0) {
//			jsonin = JSONObject.fromObject(sb.toString());
//			Iterator iterator = jsonin.keys();
//			while (iterator.hasNext()) {
//				String key = (String) iterator.next();
//				if(sqlValidate(jsonin.getString(key))) {
//					System.out.println("have sql");
//					resp.sendRedirect("login.html");
//				}
//			}
//		}
//		Enumeration params = req.getParameterNames();
//        String sql = "";
//        while (params.hasMoreElements()) {
//            //得到参数名
//            String name = params.nextElement().toString();
//
//            //得到参数对应值
//            String[] value = req.getParameterValues(name);
//            for (int i = 0; i < value.length; i++) {
//                sql = sql + value[i];
//            }
//        }
//
//        //有sql关键字，跳转到error.html
//        if (sqlValidate(sql)) {
//            throw new IOException("您发送请求中的参数中含有非法字符");
//            //String ip = req.getRemoteAddr();
//        } else {
//        	System.out.println("sql Filter do chain");
//            chain.doFilter(req,resp);
//        }
            chain.doFilter(req,resp);
	}

	// 校验
	protected static boolean sqlValidate(String str) {
		str = str.toLowerCase();// 统一转为小写
		// String badStr = "and|exec";
		String badStr = "'|and|exec|execute|insert|select|delete|update|count|drop|chr|mid|master|truncate|char|declare|sitename|net user|xp_cmdshell|or|like";
		
		// 过滤掉的sql关键字，可以手动添加
		String[] badStrs = badStr.split("\\|");
		for (int i = 0; i < badStrs.length; i++) {
			if (str.indexOf(badStrs[i]) != -1) {
//				System.out.println("匹配到：" + badStrs[i]);
				return true;
			}
		}
		return false;
	}
}
