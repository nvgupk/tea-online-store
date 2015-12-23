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

import org.apache.log4j.Logger;

import com.teaonlinestore.dao.TeaDao;
import com.teaonlinestore.dao.TeaDaoHibernate;
import com.teaonlinestore.model.Category;
import com.teaonlinestore.service.CategoryManager;
import com.teaonlinestore.service.CategoryManagerInterface;
import com.teaonlinestore.service.ProductManager;
import com.teaonlinestore.service.ProductManagerInterface;
import com.teaonlinestore.service.TeaManager;
import com.teaonlinestore.service.TeaManagerInterface;

public class HeaderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(HeaderController.class);
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CategoryManagerInterface categoryManager = new CategoryManager();
		ProductManagerInterface productManager = new ProductManager();
		List<Category> categories = categoryManager.getCategoryByVisible(true);
		Map<Category, List<String>> categoryKinds = new HashMap<Category, List<String>>(5);
		for(Category category:categories) {
			categoryKinds.put(category, productManager.getProductKindsByCategory(category));
		}
		request.setAttribute("categoryKinds", categoryKinds);
		String path = (String) request.getAttribute("path");
		RequestDispatcher view;
		if(!path.equals("")) {
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
