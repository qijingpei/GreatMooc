package com.greatmooc.web.filter;

/*
 * 前台页面用户登陆的过滤器
 */
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

public class LoginFilter implements Filter {

	@Override
	public void destroy() {
		// TODO 自动生成的方法存根

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		/*
		 * 1.从session中获取用户信息
		 * 2.判断如果session中存在用户信息，放行！
		 * 3.否则，保存错误信息，转发到login.jsp显示
		 */
		HttpServletRequest httpRequest=(HttpServletRequest) request;
		User user=(User)httpRequest.getSession().getAttribute("session_user");
		//如果session_admin存在，放行！
		Admin admin=(Admin) httpRequest.getSession().getAttribute("session_admin");
		if(admin!=null) {
			chain.doFilter(request, response);
			return ;
		}
		if(user!=null) {
			chain.doFilter(request, response);
			
		} else {
			httpRequest.getRequestDispatcher("/jsps/user/login.jsp").forward(httpRequest, response);
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO 自动生成的方法存根

	}

}
