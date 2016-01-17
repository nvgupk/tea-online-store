package com.teaonlinestore.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.teaonlinestore.model.Category;
import com.teaonlinestore.service.CategoryManager;
import com.teaonlinestore.service.CategoryManagerInterface;
import com.teaonlinestore.service.ProductManagerFactory;
import com.teaonlinestore.service.ProductManagerInterface;

/**
 * Servlet Filter implementation class HeaderFilter
 */
public class HeaderFilter implements Filter {

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		CategoryManagerInterface categoryManager = new CategoryManager();
		List<Category> categories = categoryManager.getCategoryByVisible(true);
		Map<Category, List<String>> categoryKinds = new HashMap<Category, List<String>>(5);
		for(Category category:categories) {
			ProductManagerInterface productManager = ProductManagerFactory.createProductManager(category);
			categoryKinds.put(category, productManager.getProductKinds());
		}
		request.setAttribute("categoryKinds", categoryKinds);
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
