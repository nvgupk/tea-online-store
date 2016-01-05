package com.teaonlinestore.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.teaonlinestore.model.Category;
import com.teaonlinestore.utils.HibernateUtil;

public class CategoryDaoHibernate extends GenericDaoHibernate<Category, Long> implements CategoryDao {
	public List<Category> getAllCategory() {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from Category");
		List<Category> categories = query.list();
		return categories;
	}
	
	public List<Category> getCategoryByVisible(boolean visible) {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from Category where visible = :visible");
		query.setParameter("visible", visible);
		List<Category> categories = query.list();
		return categories;
	}
	
	
}
