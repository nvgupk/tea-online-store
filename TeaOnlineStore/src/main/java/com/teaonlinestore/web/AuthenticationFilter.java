package com.teaonlinestore.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.teaonlinestore.model.Customer;
import com.teaonlinestore.service.CustomerManager;
import com.teaonlinestore.service.CustomerManagerInterface;

/**
 * Servlet Filter implementation class AuthenticationFilter
 */
public class AuthenticationFilter implements Filter {
	
	public void init(FilterConfig fConfig) throws ServletException {

	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		httpRequest.setCharacterEncoding("UTF-8");
		HttpSession session = httpRequest.getSession();
		Customer currentSessionUser = (Customer) session.getAttribute("currentSessionUser");
		Cookie[] requestCookies = null;
		Cookie loginCookie = null;
		if(currentSessionUser == null) {
			requestCookies = httpRequest.getCookies();
			if(requestCookies != null) {
				for(Cookie c : requestCookies) {
					if(c.getName().equals(session.getServletContext().getInitParameter("sessionCookieName"))) {
						loginCookie = c;
						break;
					}
				}
				if(loginCookie != null) {
					Long customerId = Long.valueOf(loginCookie.getValue());
					CustomerManagerInterface customerManager = new CustomerManager();
					currentSessionUser = customerManager.getCustomerById(customerId);
					session.setAttribute("currentSessionUser", currentSessionUser);
				}
			}
		}
		chain.doFilter(request, response);
	}

	public void destroy() {
		
	}
}
