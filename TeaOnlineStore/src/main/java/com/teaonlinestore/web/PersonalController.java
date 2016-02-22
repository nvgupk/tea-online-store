package com.teaonlinestore.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.teaonlinestore.model.Address;
import com.teaonlinestore.model.Customer;
import com.teaonlinestore.model.Purchase;
import com.teaonlinestore.service.CustomerManager;
import com.teaonlinestore.service.CustomerManagerInterface;
import com.teaonlinestore.service.PurchaseManager;
import com.teaonlinestore.service.PurchaseManagerInterface;
import com.teaonlinestore.web.component.PersonalParameterValidation;
import com.teaonlinestore.web.component.RequestsParametersValidation;

public class PersonalController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Customer currentUser = (Customer) session.getAttribute("currentSessionUser");
		PurchaseManagerInterface purchaseManager = new PurchaseManager();
		List<Purchase> purchases = purchaseManager.getPurchaseByCustomer(currentUser);
		request.setAttribute("purchases", purchases);
		RequestDispatcher view = request.getRequestDispatcher("personal.jsp");
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Customer currentUser = (Customer) session.getAttribute("currentSessionUser");
		CustomerManagerInterface customerManager = new CustomerManager();
		String ajaxEmail = request.getParameter("ajaxEmail");
		if(ajaxEmail != null) {
			if(ajaxEmail.equals(currentUser.getEmail())) {
				JSONObject json = new JSONObject();
				json.put("isEmailOfCurrentUser", true);
				response.getWriter().write(json.toJSONString());
				return;
			}
			Customer customer = customerManager.getRegisteredCustomerByEmail(ajaxEmail);
			JSONObject json = new JSONObject();
			if(customer == null) {
				json.put("isRegistered", false);
			} else {
				json.put("isRegistered", true);
			}
			response.getWriter().write(json.toJSONString());
			return;
		}
		String ajaxOldPassword = request.getParameter("ajaxOldPassword");
		if(ajaxOldPassword != null) {
			JSONObject json = new JSONObject();
			if(ajaxOldPassword.equals(currentUser.getPassword())) {
				json.put("isPasswordCorrect", true);
			} else {
				json.put("isPasswordCorrect", false);
			}
			response.getWriter().write(json.toJSONString());
			return;
		}
		RequestsParametersValidation parametersValidation = new PersonalParameterValidation();
		if(!parametersValidation.checkForNull(request)) {
			JSONObject json = new JSONObject();
			json.put("isParameterCorrect", false);
			response.getWriter().write(json.toJSONString());
			return;
		}
		String oldPassword = request.getParameter("old-password");
		String newPassword = request.getParameter("password");
		if(newPassword != null && !newPassword.equals("")) {
			if(currentUser.getPassword().equals(oldPassword)) {
				currentUser.setPassword(newPassword);
			} else {
				JSONObject json = new JSONObject();
				json.put("isPasswordCorrect", false);
				response.getWriter().write(json.toJSONString());
				return;
			}
		}
		currentUser.setEmail(request.getParameter("email"));
		currentUser.setFirstName(request.getParameter("name"));
		currentUser.setLastName(request.getParameter("surname"));
		currentUser.setPhoneNumber(request.getParameter("phonenumber"));
		Address newAddress = new Address();
		newAddress.setCity(request.getParameter("city"));
		newAddress.setStreet(request.getParameter("street"));
		currentUser.setAddress(newAddress);
		JSONObject json = new JSONObject();
		json.put("isPasswordCorrect", true);
		json.put("isParameterCorrect", true);
		response.getWriter().write(json.toJSONString());
	}

}
