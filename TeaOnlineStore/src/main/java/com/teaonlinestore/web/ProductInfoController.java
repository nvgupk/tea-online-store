package com.teaonlinestore.web;

import java.io.IOException;
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

public class ProductInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Properties infoProperties = FileUtil.getProperties("info-message.properties");
		RequestsParametersValidation parametersValidation = new ProductInfoParameterValidation();
		if(!parametersValidation.checkForNumber(request)) {
			redirectToInfoPage(request, response, infoProperties.getProperty("incorrect-address-parameters"));
		}
		Long categoryId = Long.valueOf(request.getParameter("category_id"));
		Long productId = Long.valueOf(request.getParameter("product_id"));
		CategoryManagerInterface categoryManager = new CategoryManager();
		Category category = categoryManager.getById(categoryId);
		GeneralProductManagerInterface productManager = ProductManagerFactory.createProductManager(category);
		if(productManager == null) {
			redirectToInfoPage(request, response, infoProperties.getProperty("incorrect-address-parameters"));
		}
		Product chosenProduct = productManager.getProductById(productId);
		if(chosenProduct == null) {
			redirectToInfoPage(request, response, infoProperties.getProperty("incorrect-address-parameters"));
		}
		AttributeManagerInterface attributeManager = new AttributeManager();
		List<Attribute> attributes = attributeManager.getAttributesByIds(productManager.getAttributeNames());
		Map<Attribute, String> attributeValues = productManager.getAttributeValues(attributes, chosenProduct);
		request.setAttribute("product", chosenProduct);
		request.setAttribute("attributeValues", attributeValues);
		RequestDispatcher view = request.getRequestDispatcher("productinfo.jsp");
		view.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	private void redirectToInfoPage(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
		request.setAttribute("message", message);
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/infopage.jsp");
		view.forward(request, response);
	}
}
