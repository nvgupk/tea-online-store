package com.teaonlinestore.web;

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

import org.apache.log4j.Logger;

/**
 * Servlet Filter implementation class PageRedirectFilter
 */
public class PageRedirectFilter implements Filter {
	private static final Logger LOG = Logger.getLogger(PageRedirectFilter.class);
	
	public void init(FilterConfig fConfig) throws ServletException {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String categoryIdStr = httpRequest.getParameter("category_id");
		Long categoryId = null;
		if(categoryIdStr != null) {
			try{
				categoryId = Long.valueOf(categoryIdStr);
			} catch(NumberFormatException ex) {
				LOG.info("Illegal categoryId value", ex);
			}
		}
		RequestDispatcher rd;
		if(categoryId != null) {
			rd = httpRequest.getRequestDispatcher("/ProductController");
			rd.forward(httpRequest, httpResponse);
		} else {
			rd = httpRequest.getRequestDispatcher("/WelcomeController");
			rd.forward(httpRequest, httpResponse);
		}
	}
	
	public void destroy() {
		
	}
}
