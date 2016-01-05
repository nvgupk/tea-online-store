package com.teaonlinestore.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.teaonlinestore.model.Category;
import com.teaonlinestore.service.CategoryManager;
import com.teaonlinestore.service.CategoryManagerInterface;
import com.teaonlinestore.service.ProductManagerFactory;
import com.teaonlinestore.service.ProductManagerInterface;

public class HeaderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(HeaderController.class);
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session == null) {
			System.out.println("NULL");
		} else {
			System.out.println("EXIST");
		}
		CategoryManagerInterface categoryManager = new CategoryManager();
		List<Category> categories = categoryManager.getCategoryByVisible(true);
		Map<Category, List<String>> categoryKinds = new HashMap<Category, List<String>>(5);
		for(Category category:categories) {
			ProductManagerInterface productManager = ProductManagerFactory.createProductManager(category);
			categoryKinds.put(category, productManager.getProductKinds());
		}
		request.setAttribute("categoryKinds", categoryKinds);
		String categoryIdStr = request.getParameter("category_id");
		Long categoryId = null;
		if(categoryIdStr != null) {
			categoryId = Long.valueOf(request.getParameter("category_id"));
		}
		RequestDispatcher view;
		if(categoryId != null) {
			view = request.getRequestDispatcher("/FilterBarController");
			view.forward(request, response);
		} else {
			view = request.getRequestDispatcher("welcome.jsp");
			view.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
