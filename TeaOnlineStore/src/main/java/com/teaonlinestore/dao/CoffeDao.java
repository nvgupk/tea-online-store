package com.teaonlinestore.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.teaonlinestore.model.Attribute;
import com.teaonlinestore.model.Coffe;
import com.teaonlinestore.model.Product;

public interface CoffeDao extends GenericDao<Coffe, Long> {
	public List<String> getCoffeKinds();
	public Map<Attribute, List<String>> getAttributeValues(Collection<Attribute> attributes);
	public Map<Attribute, String> getAttributeValues(Collection<Attribute> attributes, Product product);
	public Double getCoffeMaxPrice();
	public Double getCoffeMinPrice();
	public List<Coffe> getEntitysByAttributes(Map<String, List<String>> attributeValues, Double minPrice, Double maxPrice, String orderBy);
}
