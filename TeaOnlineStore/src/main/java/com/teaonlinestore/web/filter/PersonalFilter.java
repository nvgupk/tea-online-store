package com.teaonlinestore.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.teaonlinestore.model.Customer;


public class PersonalFilter implements Filter {
	
	public void init(FilterConfig fConfig) throws ServletException {
		
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession();
		Customer currentSessionUser = (Customer) session.getAttribute("currentSessionUser");
		if(currentSessionUser == null) {
			httpResponse.sendRedirect("login.jsp");
		} else {
			RequestDispatcher view = httpRequest.getRequestDispatcher("/Personal");
			view.forward(httpRequest, httpResponse);
		}
	}

	public void destroy() {

	}
}
