package com.teaonlinestore.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

import com.teaonlinestore.model.Product;
import com.teaonlinestore.service.ProductManager;
import com.teaonlinestore.service.ProductManagerInterface;

public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductManagerInterface productManager = new ProductManager();
		String searchQuery = request.getParameter("searchQuery");
		List<Product> products = productManager.getProductsByPartialName(searchQuery);
		request.setAttribute("products", products);
		RequestDispatcher view = request.getRequestDispatcher("searchresult.jsp");
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductManagerInterface productManager = new ProductManager();
		String searchQuery = request.getParameter("searchQuery");
		List<Product> products = productManager.getProductsByPartialName(searchQuery);
		JSONArray jsonArray = productManager.parseProductsToJSONArray(products);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(jsonArray.toJSONString());
	}

}
