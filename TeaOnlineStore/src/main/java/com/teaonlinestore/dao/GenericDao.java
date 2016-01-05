package com.teaonlinestore.dao;

import java.io.Serializable;

public interface GenericDao<T, ID extends Serializable> {

	public void save(T entity);

	public void update(T entity);

	public void delete(T entity);

	public T findByID(Class type, Long id);
	
}
