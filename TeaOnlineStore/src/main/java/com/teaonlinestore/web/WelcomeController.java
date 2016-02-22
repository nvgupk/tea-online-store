package com.teaonlinestore.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.teaonlinestore.model.Product;
import com.teaonlinestore.service.ProductManager;
import com.teaonlinestore.service.ProductManagerInterface;

public class WelcomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductManagerInterface productManager = new ProductManager();
		Integer numberOfProductsOnPage = Integer.valueOf(getServletContext().getInitParameter("numberOfProductsOnPage"));
		List<Product> topProducts = productManager.getMostPopularProducts(numberOfProductsOnPage);
		request.setAttribute("products", topProducts);
		RequestDispatcher view = request.getRequestDispatcher("welcome.jsp");
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
