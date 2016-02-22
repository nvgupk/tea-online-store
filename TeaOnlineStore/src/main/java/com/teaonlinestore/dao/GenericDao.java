package com.teaonlinestore.dao;

import java.io.Serializable;
import java.util.Set;

public interface GenericDao<T, ID extends Serializable> {

	public void save(T entity);
	
	public void update(T entity);

	public void delete(T entity);

	public T findByID(Class type, ID id);
	
	public  Set<String> getAttributeNames(Class type);
}
