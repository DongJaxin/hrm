package com.hrm.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hrm.commons.beans.User;

public class LoginFilter implements Filter {

	private String[] IGNORE_URI = {"/index.jsp","/loginForm.jsp","/login.do","/404.html"};
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		String requestURI = request.getRequestURI();
		boolean flag = false;
		for(String s:IGNORE_URI){
			if(requestURI.contains(s)){
				flag = true;
				arg2.doFilter(request, response);
				break;
			}
		}
		if(!flag){
			User login_user = (User) request.getSession().getAttribute("login_user");
			if(login_user == null){
				request.setAttribute("message", "请登录后访问网站");
				request.getRequestDispatcher("/jsp/loginForm.jsp").forward(request, response);
			}else{
				arg2.doFilter(request, response);
			}
		}
		
	}

}
