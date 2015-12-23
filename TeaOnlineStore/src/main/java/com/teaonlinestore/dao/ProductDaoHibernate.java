package com.teaonlinestore.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.teaonlinestore.model.Category;
import com.teaonlinestore.model.Product;
import com.teaonlinestore.utils.HibernateUtil;

public class ProductDaoHibernate extends GenericDaoHibernate<Product, Long> implements ProductDao {
	public List<String> getProductKindsByCategory(Category category) {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("select distinct kind from Product where category_id = :category");
		query.setParameter("category", category);
		List<String> kinds = query.list();
		return kinds;
	}
}
