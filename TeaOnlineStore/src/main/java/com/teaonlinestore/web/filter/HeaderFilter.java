package com.teaonlinestore.web.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.teaonlinestore.model.Category;
import com.teaonlinestore.service.CategoryManager;
import com.teaonlinestore.service.CategoryManagerInterface;
import com.teaonlinestore.service.GeneralProductManagerInterface;
import com.teaonlinestore.service.ProductManagerFactory;

public class HeaderFilter implements Filter {

	
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		CategoryManagerInterface categoryManager = new CategoryManager();
		List<Category> categories = categoryManager.getCategoryByVisible(true);
		Map<Category, List<String>> categoryKinds = new HashMap<Category, List<String>>(5);
		for(Category category:categories) {
			GeneralProductManagerInterface productManager = ProductManagerFactory.createProductManager(category);
			categoryKinds.put(category, productManager.getProductKinds());
		}
		request.setAttribute("categoryKinds", categoryKinds);
		chain.doFilter(request, response);
	}
	
	public void destroy() {
		
	}

}
