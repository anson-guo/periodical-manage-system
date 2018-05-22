package com.nine.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LoginFilter implements Filter {

	 /**
     * Default constructor. 
     */
    public LoginFilter() {
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 * description:统一请求编码为UTF—8
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;	
//		System.out.println("this is login Filter");
//		Referer: http://localhost:8080/periodicalWeb_my/login.html
		String refererStr = req.getHeader("Referer");
		//获取根目录所对应的绝对路径
        String currentURL = req.getRequestURI();
        //截取到当前文件名用于比较
        String targetURL = currentURL.substring(currentURL.indexOf("/",1),currentURL.length());
//        System.out.println("targetURl is "+ targetURL);
//        System.out.println("contains is "+targetURL.contains("index.html"));
        if(targetURL.contains("index.html")){
    		//获取父级标签
//        	System.out.println("referer is "+refererStr);
        	if(refererStr == null|| refererStr.equals("")) {
        		resp.sendRedirect("login.html");
        	}
        	else if(req.getSession().getAttribute("login")==null){ 
//        		System.out.println("session login is "+req.getSession().getAttribute("login"));
//        		System.out.println("login filter not out");
        		resp.sendRedirect("login.html");
//        		chain.doFilter(req, resp);
        	} 
        }
		chain.doFilter(req, resp);
	}


}
