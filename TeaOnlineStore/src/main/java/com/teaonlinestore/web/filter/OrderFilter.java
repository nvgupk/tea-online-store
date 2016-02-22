package com.teaonlinestore.web.filter;

import java.io.IOException;
import java.util.Properties;

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

import com.teaonlinestore.utils.FileUtil;
import com.teaonlinestore.web.component.ShoppingCart;

public class OrderFilter implements Filter {

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession();
		ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");
		RequestDispatcher rd = null;
		if(shoppingCart == null || shoppingCart.getSize().equals(0)) {
			Properties infoProperties = FileUtil.getProperties("info-message.properties");
			request.setAttribute("message", infoProperties.getProperty("shopping-cart-is-empty"));
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/infopage.jsp");
			view.forward(request, response);
		} else {
			rd = httpRequest.getRequestDispatcher("/Order");
			rd.forward(httpRequest, httpResponse);
		}
	}
	
	public void destroy() {
		
	}
}
