package com.teaonlinestore.web;

import java.io.IOException;
import java.util.Properties;

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
import com.teaonlinestore.utils.FileUtil;
import com.teaonlinestore.web.component.RegistrationParameterValidation;
import com.teaonlinestore.web.component.RequestsParametersValidation;

public class RegistrationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		Properties infoProperties = FileUtil.getProperties("info-message.properties");
		if(email == null) {
			redirectToInfoPage(request, response, infoProperties.getProperty("incorrect-form-parameters"));
		}
		CustomerManagerInterface customerManager = new CustomerManager();
		Customer customer = customerManager.getRegisteredCustomerByEmail(email);
		Boolean isCustomerRegistered = customer == null ? false : true;
		String isAjax = request.getParameter("ajax");
		if (isAjax != null) {
			JSONObject json = new JSONObject();
			json.put("registrationStatus", isCustomerRegistered);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json.toJSONString());
		} else {
			RequestsParametersValidation parametersValidation = new RegistrationParameterValidation();
			if(!parametersValidation.checkForNull(request) || isCustomerRegistered) {
				redirectToInfoPage(request, response, infoProperties.getProperty("incorrect-form-parameters"));
			}
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
	
	private void redirectToInfoPage(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
		request.setAttribute("message", message);
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/infopage.jsp");
		view.forward(request, response);
	}
	
}
