package com.teaonlinestore.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.teaonlinestore.model.Address;
import com.teaonlinestore.model.Customer;
import com.teaonlinestore.service.CustomerManager;
import com.teaonlinestore.service.CustomerManagerInterface;

/**
 * Servlet implementation class Registration
 */
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registration() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); 
		String email = request.getParameter("email");
		CustomerManagerInterface customerManager = new CustomerManager();
		Customer customer = customerManager.getCustomerByEmail(email);
		Boolean isCustomerRegistered = null;
		if(customer == null) {
			isCustomerRegistered = false;
		} else {
			isCustomerRegistered = true;
		}
		String isAjax = request.getParameter("ajax");
		if (isAjax != null) {
			JSONObject json = new JSONObject();
			json.put("registrationStatus", isCustomerRegistered);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json.toJSONString());
		} else if(!isCustomerRegistered){
			Customer newCustomer = new Customer();
			newCustomer.setEmail(email);
			newCustomer.setFirstName(request.getParameter("name"));
			newCustomer.setLastName(request.getParameter("surname"));
			newCustomer.setPassword(request.getParameter("password"));
			newCustomer.setPhoneNumber(request.getParameter("phonenumber"));
			Address address = new Address();
			address.setCity(request.getParameter("city"));
			address.setStreet(request.getParameter("street"));
			newCustomer.setAddress(address);
			customerManager.saveCustomer(newCustomer);
			HttpSession session = request.getSession();
			session.setMaxInactiveInterval(30*24*60*60);
			session.setAttribute("currentSessionUser", newCustomer);
			Cookie userIdCookie = new Cookie(session.getServletContext().getInitParameter("sessionCookieName"), String.valueOf(newCustomer.getCustomerId()));
			userIdCookie.setMaxAge(30*24*60*60);
			response.addCookie(userIdCookie);
			response.sendRedirect("welcome.jsp");	
		}
	}

}
