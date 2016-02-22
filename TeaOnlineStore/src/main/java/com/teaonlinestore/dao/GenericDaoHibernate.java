package com.teaonlinestore.dao;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.persister.entity.AbstractEntityPersister;

import com.teaonlinestore.utils.HibernateUtil;

public abstract class GenericDaoHibernate<T, ID extends Serializable> implements GenericDao<T, ID> {
	public void delete(T entity) {
		Session session = HibernateUtil.getSession();
		session.delete(entity);
	}
	
	public T findByID(Class type, ID id) {
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
	
	public Set<String> getAttributeNames(Class type) {
		AbstractEntityPersister aep = (AbstractEntityPersister) HibernateUtil.getSessionFactory().getClassMetadata(type);
		String[] propertyNames = aep.getPropertyNames();
		return new HashSet<String>(Arrays.asList(propertyNames));
	}
	
}
