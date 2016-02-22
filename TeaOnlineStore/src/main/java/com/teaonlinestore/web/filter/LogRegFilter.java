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


public class LogRegFilter implements Filter {

	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String email = request.getParameter("email");
		String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
		HttpSession session = httpRequest.getSession();
		Customer currentSessionUser = (Customer) session.getAttribute("currentSessionUser");
		if(currentSessionUser != null) {
			httpResponse.sendRedirect("");
		} else if(email == null){
			RequestDispatcher view = null;
			switch(path) {
				case "/Registration":
					view = httpRequest.getRequestDispatcher("registration.jsp");
					view.forward(request, response);
					break;
				case "/Login":
					view = httpRequest.getRequestDispatcher("login.jsp");
					view.forward(request, response);
					break;
				default:
					chain.doFilter(request, response);
			}
		} else {
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
