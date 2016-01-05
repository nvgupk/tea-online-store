package com.teaonlinestore.dao;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.teaonlinestore.service.CategoryManager;
import com.teaonlinestore.utils.HibernateUtil;

public abstract class GenericDaoHibernate<T, ID extends Serializable> implements GenericDao<T, ID> {
	public void delete(T entity) {
		Session session = HibernateUtil.getSession();
		session.delete(entity);
	}
	
	public T findByID(Class type, Long id) {
		Session session = HibernateUtil.getSession();
		T entity = (T) session.get(type, id);
		return entity;
	}
	
	public void save(T entity) {
		Session session = HibernateUtil.getSession();
		session.save(entity);
	}
	
	public void update(T entity) {
		Session session = HibernateUtil.getSession();
		session.update(entity);
	}
}
