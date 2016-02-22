package com.teaonlinestore.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.teaonlinestore.model.Customer;
import com.teaonlinestore.service.CustomerManager;
import com.teaonlinestore.service.CustomerManagerInterface;

public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("currentSessionUser");
		Cookie[] requestCookies = request.getCookies();
		Cookie loginCookie = null;
		if (requestCookies != null) {
			for (Cookie c : requestCookies) {
				if (c.getName().equals(session.getServletContext().getInitParameter("sessionCookieName"))) {
					loginCookie = c;
					break;
				}
			}
		}
		if(loginCookie != null) {
			loginCookie.setMaxAge(0);
			response.addCookie(loginCookie);
		}
		response.sendRedirect("");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		Boolean isRegistered = true;
		CustomerManagerInterface customerManager = new CustomerManager();
		Customer customer = customerManager.getRegisteredCustomerByEmailAndPassword(email, password);
		if(customer == null) {
			isRegistered = false;
		}
		RequestDispatcher view = null;
		if(!isRegistered) {
			request.setAttribute("isRegistered", isRegistered);
			view = request.getRequestDispatcher("login.jsp");
			view.forward(request, response);
		} else {
			HttpSession session = request.getSession();
			session.setMaxInactiveInterval(30*24*60*60);
			session.setAttribute("currentSessionUser", customer);
			Cookie userIdCookie = new Cookie(session.getServletContext().getInitParameter("sessionCookieName"), String.valueOf(customer.getCustomerId()));
			userIdCookie.setMaxAge(30*24*60*60);
			response.addCookie(userIdCookie);
			response.sendRedirect("welcome.jsp");
		}
	}

}
