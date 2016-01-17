package com.teaonlinestore.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.teaonlinestore.model.Category;
import com.teaonlinestore.model.Product;

public interface ProductManagerInterface {
	public List<String> getProductKinds();
	public Map<String, String> getAttributeNamesUA();
	public Map<String, List<String>> getAttributeValues(Set<String> attributes);
	public List<? extends Product> getProductsByAttributes(Map<String, List<String>> attributeValues, Double minPrice, Double maxPrice);
	public List<? extends Product> getProductsByAttributes(Map<String, List<String>> attributeValues);
	public Double getProductMaxPrice();
	public Double getProductMinPrice();
}
