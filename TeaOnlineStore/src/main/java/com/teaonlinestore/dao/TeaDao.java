package com.teaonlinestore.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.teaonlinestore.model.Tea;

public interface TeaDao extends GenericDao<Tea, Long> {
	public List<String> getTeaKinds();
	public List<String>  getAttributeNames();
	public Map<String, List<?>> getAttributeValues(Set<String> attributes);
}
