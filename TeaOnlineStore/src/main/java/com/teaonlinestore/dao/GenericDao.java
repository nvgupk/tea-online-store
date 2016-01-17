package com.teaonlinestore.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.teaonlinestore.model.Tea;

public interface GenericDao<T, ID extends Serializable> {

	public void save(T entity);

	public void update(T entity);

	public void delete(T entity);

	public T findByID(Class type, Long id);
	
	public List<T> getEntitysByAttributes(Map<String, List<String>> attributeValues, Double minPrice, Double maxPrice, Class type);
	
}
