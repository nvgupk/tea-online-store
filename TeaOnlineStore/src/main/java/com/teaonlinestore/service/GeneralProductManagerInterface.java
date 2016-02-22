package com.teaonlinestore.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.teaonlinestore.model.Attribute;
import com.teaonlinestore.model.Product;

public interface GeneralProductManagerInterface {
	public List<String> getProductKinds();
	public Product getProductById(Long id);
	public Set<String> getAttributeNames();
	public Map<Attribute, List<String>> getAttributeValues(Collection<Attribute> attributes);
	public Map<Attribute, String> getAttributeValues(Collection<Attribute> attributes, Product product);
	public List<? extends Product> getProductsByAttributes(Map<String, List<String>> attributeValues, Double minPrice, Double maxPrice, String orderBy);
	public List<? extends Product> getProductsByAttributes(Map<String, List<String>> attributeValues, String orderBy);
	public Double getProductMaxPrice();
	public Double getProductMinPrice();
	
}
