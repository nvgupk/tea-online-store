package com.teaonlinestore.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.teaonlinestore.model.Category;
import com.teaonlinestore.service.CategoryManager;
import com.teaonlinestore.service.CategoryManagerInterface;

public class HeaderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(HeaderController.class);
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CategoryManagerInterface categoryManager = new CategoryManager();
		List<Category> categories = categoryManager.getCategoryByVisible(true);
		request.setAttribute("categories", categories);
		RequestDispatcher view = request.getRequestDispatcher("tea.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
