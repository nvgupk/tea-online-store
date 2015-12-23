package com.teaonlinestore.service;

import java.util.List;

import com.teaonlinestore.model.Category;

public interface CategoryManagerInterface {
	public List<Category> getAllCategory();
	public List<Category> getCategoryByVisible(boolean visible);
	public Category getById(Long id);
}
