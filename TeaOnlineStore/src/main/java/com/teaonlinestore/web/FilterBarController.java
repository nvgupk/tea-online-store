package com.teaonlinestore.web;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.teaonlinestore.model.Category;
import com.teaonlinestore.service.CategoryManager;
import com.teaonlinestore.service.CategoryManagerInterface;
import com.teaonlinestore.service.ProductManagerFactory;
import com.teaonlinestore.service.ProductManagerInterface;
import com.teaonlinestore.utils.HibernateUtil;

/**
 * Servlet implementation class FilterBarController
 */
public class FilterBarController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(FilterBarController.class);
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String categoryIdStr = request.getParameter("category_id");
		Long categoryId = null;
		if(categoryIdStr != null) {
			categoryId = Long.valueOf(request.getParameter("category_id"));
		}
		Map<String, String> attributes = new HashMap<String, String>();
		Map<String, List<?>> attributeValues = new HashMap<String, List<?>>();
		Category category = null;
		if(categoryId != null) {
			CategoryManagerInterface categoryManager = new CategoryManager();
			category = categoryManager.getById(categoryId);
			ProductManagerInterface productManager = ProductManagerFactory.createProductManager(category);
			if(productManager != null) {
				attributes = productManager.getAttributeNamesUA();
				attributeValues = productManager.getAttributeValues(attributes.keySet());
			}		
		}
		
		for(String attr : attributes.keySet()) {
			String[] par = request.getParameterValues(attr);
			request.setAttribute(attr, par);
		}
		request.setAttribute("category", category);
		request.setAttribute("attributes", attributes);
		request.setAttribute("attributeValues", attributeValues);
		RequestDispatcher view = request.getRequestDispatcher("product.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
