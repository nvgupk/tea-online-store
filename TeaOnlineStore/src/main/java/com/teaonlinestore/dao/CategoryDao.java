package com.teaonlinestore.dao;

import java.util.List;

import com.teaonlinestore.model.Category;

public interface CategoryDao extends GenericDao<Category, Long>{
	public List<Category> getAllCategory();
	public List<Category> getCategoryByVisible(boolean visible);
}
