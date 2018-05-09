package com.nine.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFilter implements Filter {

	FilterConfig config;
	@Override
	public void destroy() {
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.config = filterConfig;
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) arg0;
		HttpServletResponse resp = (HttpServletResponse) arg1;
		
		String encoding = config.getInitParameter("encoding");
		if(null == encoding) {
			req.setCharacterEncoding("UTF-8");
		}else {
			req.setCharacterEncoding(encoding);
		}
		
		String exceptPage = config.getInitParameter("exceptPage");
		if(null != exceptPage && !"".equals(exceptPage.trim())) {
			String[] exceptPages = exceptPage.split(";");
			for (String except : exceptPages) {
//				System.out.println("except : " + except);
//				System.out.println("URI : " + req.getRequestURI());
//				System.out.println("indexOf : "+ req.getRequestURI().indexOf(except));
				if (req.getRequestURI().indexOf(except) != -1) {
//					System.out.println("doFilter");
					arg2.doFilter(arg0, arg1);
					return;
				}
			}
		}
	
		
		
		String userName = (String) req.getSession().getAttribute("username");
		if(null != userName) {
			arg2.doFilter(arg0, arg1);
			
		}else {
//			System.out.println("username :" + userName);
//			System.out.println("req contextPath "+req.getContextPath() + "/index.jsp");
			resp.sendRedirect(req.getContextPath() + "/login.jsp");
			
//			System.out.println("end");
		}
	}


}
