package com.teaonlinestore.web;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.teaonlinestore.model.Attribute;
import com.teaonlinestore.model.Category;
import com.teaonlinestore.model.Product;
import com.teaonlinestore.service.AttributeManager;
import com.teaonlinestore.service.AttributeManagerInterface;
import com.teaonlinestore.service.CategoryManager;
import com.teaonlinestore.service.CategoryManagerInterface;
import com.teaonlinestore.service.GeneralProductManagerInterface;
import com.teaonlinestore.service.ProductManagerFactory;
import com.teaonlinestore.utils.FileUtil;
import com.teaonlinestore.web.component.ProductInfoParameterValidation;
import com.teaonlinestore.web.component.RequestsParametersValidation;

public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Properties infoProperties = FileUtil.getProperties("info-message.properties");
		RequestsParametersValidation parametersValidation = new ProductInfoParameterValidation();
		if(!parametersValidation.checkForNumber(request)) {
			redirectToInfoPage(request, response, infoProperties.getProperty("incorrect-address-parameters"));
		}
		Long categoryId = Long.valueOf(request.getParameter("category_id"));
		CategoryManagerInterface categoryManager = new CategoryManager();
		Category category = categoryManager.getById(categoryId);
		GeneralProductManagerInterface productManager = ProductManagerFactory.createProductManager(category);
		if(productManager == null) {
			redirectToInfoPage(request, response, infoProperties.getProperty("incorrect-address-parameters"));
		}
		AttributeManagerInterface attributeManager = new AttributeManager();
		List<Attribute> attributes = attributeManager.getAttributesByIds(productManager.getAttributeNames());
		Map<Attribute, List<String>> attributeValues = productManager.getAttributeValues(attributes);	
		Map<String, List<String>> checkedAttributeValues = new HashMap<String, List<String>>();
		for(Attribute attr : attributeValues.keySet()) {
			String[] checkedParameters = request.getParameterValues(attr.getAttrName());
			request.setAttribute(attr.getAttrName(), checkedParameters);
			if(checkedParameters != null) {
				checkedAttributeValues.put(attr.getAttrName(), Arrays.asList(checkedParameters));
			}
		}
		Double productHighestPrice = productManager.getProductMaxPrice();
		Double productLowestPrice = productManager.getProductMinPrice();
		Double curProductMinPrice = productLowestPrice, curProductMaxPrice = productHighestPrice;
		String curProductMinPriceStr = request.getParameter("curMinPrice");
		String curProductMaxPriceStr = request.getParameter("curMaxPrice");
		String orderByValue = request.getParameter("orderBy");
		if(orderByValue == null){
			orderByValue = "none";
		}
		request.setAttribute("orderByValue", orderByValue);
		try{
			curProductMinPrice = Double.valueOf(curProductMinPriceStr);
			curProductMaxPrice = Double.valueOf(curProductMaxPriceStr);
		} catch (NullPointerException | NumberFormatException ex) {
			
		}
		List<? extends Product> products = null;
		if(curProductMinPrice != productLowestPrice || curProductMaxPrice != productHighestPrice) {
			products = productManager.getProductsByAttributes(checkedAttributeValues, curProductMinPrice, curProductMaxPrice, orderByValue);
		} else {
			products = productManager.getProductsByAttributes(checkedAttributeValues, orderByValue);
		}
		int productCount = Integer.parseInt(getServletContext().getInitParameter("numberOfProductsOnPage"));
		Integer pageCount = products.size() / productCount;
		if(pageCount*productCount < products.size() || pageCount == 0) {
			pageCount++;
		}
		request.setAttribute("curProductMinPrice", curProductMinPrice);
		request.setAttribute("curProductMaxPrice", curProductMaxPrice);
		request.setAttribute("productHighestPrice", productHighestPrice);
		request.setAttribute("productLowestPrice", productLowestPrice);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("productCount", productCount);
		request.setAttribute("products", products);
		request.setAttribute("category", category);
		request.setAttribute("attributeValues", attributeValues);
		RequestDispatcher view = request.getRequestDispatcher("product.jsp");
		view.forward(request, response);
	}
	
	private void redirectToInfoPage(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
		request.setAttribute("message", message);
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/infopage.jsp");
		view.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
}
