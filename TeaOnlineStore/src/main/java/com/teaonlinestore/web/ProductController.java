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
import com.teaonlinestore.model.Product;
import com.teaonlinestore.model.Tea;
import com.teaonlinestore.service.CategoryManager;
import com.teaonlinestore.service.CategoryManagerInterface;
import com.teaonlinestore.service.ProductManagerFactory;
import com.teaonlinestore.service.ProductManagerInterface;
import com.teaonlinestore.utils.HibernateUtil;

/**
 * Servlet implementation class FilterBarController
 */
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String categoryIdStr = request.getParameter("category_id");
		Long categoryId = null;
		if(categoryIdStr != null) {
			categoryId = Long.valueOf(request.getParameter("category_id"));
		}
		Map<String, String> attributes = new HashMap<String, String>();
		Map<String, List<String>> attributeValues = new HashMap<String, List<String>>();
		Category category = null;
		ProductManagerInterface productManager = null;
		if(categoryId != null) {
			CategoryManagerInterface categoryManager = new CategoryManager();
			category = categoryManager.getById(categoryId);
			productManager = ProductManagerFactory.createProductManager(category);
			if(productManager != null) {
				attributes = productManager.getAttributeNamesUA();
				attributeValues = productManager.getAttributeValues(attributes.keySet());
			}		
		}
		Map<String, List<String>> checkedAttributeValues = new HashMap<String, List<String>>();
		for(String attr : attributes.keySet()) {
			String[] checkedParameters = request.getParameterValues(attr);
			request.setAttribute(attr, checkedParameters);
			if(checkedParameters != null) {
				checkedAttributeValues.put(attr, Arrays.asList(checkedParameters));
			}
		}
		Double productHighestPrice = productManager.getProductMaxPrice();
		Double productLowestPrice = productManager.getProductMinPrice();
		Double curProductMinPrice = productLowestPrice, curProductMaxPrice = productHighestPrice;
		String curProductMinPriceStr = request.getParameter("curMinPrice");
		String curProductMaxPriceStr = request.getParameter("curMaxPrice");
		try{
			curProductMinPrice = Double.valueOf(curProductMinPriceStr);
			curProductMaxPrice = Double.valueOf(curProductMaxPriceStr);
		} catch (NullPointerException | NumberFormatException ex) {
			
		}
		List<? extends Product> products = null;
		if(curProductMinPrice != productLowestPrice || curProductMaxPrice != productHighestPrice) {
			products = productManager.getProductsByAttributes(checkedAttributeValues, curProductMinPrice, curProductMaxPrice);
		} else {
			products = productManager.getProductsByAttributes(checkedAttributeValues);
		}
		request.setAttribute("curProductMinPrice", curProductMinPrice);
		request.setAttribute("curProductMaxPrice", curProductMaxPrice);
		request.setAttribute("productHighestPrice", productHighestPrice);
		request.setAttribute("productLowestPrice", productLowestPrice);
		request.setAttribute("products", products);
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
