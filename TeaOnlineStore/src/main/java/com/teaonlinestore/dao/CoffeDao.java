package com.teaonlinestore.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.teaonlinestore.model.Coffe;

public interface CoffeDao extends GenericDao<Coffe, Long> {
	public List<String> getCoffeKinds();
	public List<String> getAttributeNames();
	public Map<String, List<?>> getAttributeValues(Set<String> attributes);
}
