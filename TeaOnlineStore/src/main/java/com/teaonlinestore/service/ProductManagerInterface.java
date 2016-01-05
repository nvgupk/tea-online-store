package com.teaonlinestore.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.teaonlinestore.model.Category;

public interface ProductManagerInterface {
	public List<String> getProductKinds();
	public Map<String, String> getAttributeNamesUA();
	public Map<String, List<?>> getAttributeValues(Set<String> attributes);
}
