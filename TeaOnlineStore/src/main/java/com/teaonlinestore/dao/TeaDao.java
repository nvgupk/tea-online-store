package com.teaonlinestore.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.teaonlinestore.model.Attribute;
import com.teaonlinestore.model.Product;
import com.teaonlinestore.model.Tea;

public interface TeaDao extends GenericDao<Tea, Long> {
	public List<String> getTeaKinds();
	public Map<Attribute, List<String>> getAttributeValues(Collection<Attribute> attributes);
	public Map<Attribute, String> getAttributeValues(Collection<Attribute> attributes, Product product);
	public Double getTeaMaxPrice();
	public Double getTeaMinPrice();
	public List<Tea> getEntitysByAttributes(Map<String, List<String>> attributeValues, Double minPrice, Double maxPrice, String orderBy);
}
