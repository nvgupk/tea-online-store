package com.teaonlinestore.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.teaonlinestore.model.Coffe;
import com.teaonlinestore.model.Tea;

public interface CoffeDao extends GenericDao<Coffe, Long> {
	public List<String> getCoffeKinds();
	public List<String> getAttributeNames();
	public Map<String, List<String>> getAttributeValues(Set<String> attributes);
	public Double getCoffeMaxPrice();
	public Double getCoffeMinPrice();
}
