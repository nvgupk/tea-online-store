package com.teaonlinestore.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;

import com.teaonlinestore.model.Tea;
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
	
	public List<T> getEntitysByAttributes(Map<String, List<String>> attributeValues, Double minPrice, Double maxPrice, Class type) {
		Session session = HibernateUtil.getSession();
		Criteria cr = session.createCriteria(type);
		Conjunction and = Restrictions.conjunction();
		for(String attr : attributeValues.keySet()) {
			Disjunction or = Restrictions.disjunction();
			for(String value : attributeValues.get(attr)) {
				or.add(Restrictions.eq(attr, value));
			}
			and.add(or);
		}
		cr.add(and);
		if(minPrice != null && maxPrice != null && minPrice <= maxPrice) {
			cr.add(Restrictions.between("price", minPrice, maxPrice));
		}
		return cr.list();
	}
}
