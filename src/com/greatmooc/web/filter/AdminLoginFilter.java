package com.greatmooc.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.greatmooc.domain.Admin;
import com.greatmooc.domain.User;

public class AdminLoginFilter implements Filter {

	@Override
	public void destroy() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		/*
		 * 1.从session中获取管理员信息
		 * 2.判断如果session中存在管理员信息，放行！
		 * 3.否则，保存错误信息，转发到login.jsp显示
		 */
		HttpServletRequest httpRequest=(HttpServletRequest) request;
		Admin admin=(Admin)httpRequest.getSession().getAttribute("session_admin");
		if(admin!=null) {
			chain.doFilter(request, response);
		} else {
			httpRequest.getRequestDispatcher("/adminjsps/admin/login.jsp").forward(httpRequest, response);
		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO 自动生成的方法存根
		
	}

}
