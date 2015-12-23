package com.teaonlinestore.service;

import java.util.List;

import com.teaonlinestore.model.Category;

public interface ProductManagerInterface {
	public List<String> getProductKindsByCategory(Category category);
}
