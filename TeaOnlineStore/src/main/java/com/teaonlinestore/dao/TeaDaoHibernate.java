package com.teaonlinestore.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.teaonlinestore.model.Attribute;
import com.teaonlinestore.model.Product;
import com.teaonlinestore.model.Tea;
import com.teaonlinestore.utils.HibernateUtil;

public class TeaDaoHibernate extends GenericDaoHibernate<Tea, Long> implements TeaDao {
	
	public List<String> getTeaKinds() {
		List<String> kinds = new ArrayList<String>();
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("select distinct kind from Tea");
		kinds = query.list();
		return kinds;
	}
	
	public Map<Attribute, List<String>> getAttributeValues(Collection<Attribute> attributes) {
		Map<Attribute, List<String>> attributeValues = new HashMap<Attribute, List<String>>();
		Session session = HibernateUtil.getSession();
		for(Attribute attribute : attributes) {
			Query query = session.createQuery("select distinct " + attribute.getAttrName() + " from Tea");
			List<String> selection = query.list();
			if(!selection.isEmpty()){
				attributeValues.put(attribute, query.list());
			}
		}
		return attributeValues;
	}
	
	@Override
	public Map<Attribute, String> getAttributeValues(Collection<Attribute> attributes, Product product) {
		Map<Attribute, String> attributeValues = new HashMap<Attribute, String>();
		Session session = HibernateUtil.getSession();
		for(Attribute attribute : attributes) {
			Query query = session.createQuery("select distinct " + attribute.getAttrName() + " from Tea where productId=:productId");
			query.setParameter("productId", product.getProductId());
			List<String> selection = query.list();
			if(!selection.isEmpty()){
				attributeValues.put(attribute, (String) query.list().get(0));
			}
		}
		return attributeValues;
	}
	
	@Override
	public Double getTeaMaxPrice() {
		Session session = HibernateUtil.getSession();
		Criteria cr = session.createCriteria(Tea.class);
		cr.setProjection(Projections.max("price"));
		return (Double) cr.list().get(0);
	}
	
	@Override
	public Double getTeaMinPrice() {
		Session session = HibernateUtil.getSession();
		Criteria cr = session.createCriteria(Tea.class);
		cr.setProjection(Projections.min("price"));
		return (Double) cr.list().get(0);
	}
	
	@Override
	public List<Tea> getEntitysByAttributes(Map<String, List<String>> attributeValues, Double minPrice, Double maxPrice, String orderBy) {
		Session session = HibernateUtil.getSession();
		Criteria cr = session.createCriteria(Tea.class);
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
		if(!orderBy.equals("none")) {
			CharSequence cs = "desc";
			if(orderBy.contains(cs)) {
				cr.addOrder(Order.desc(orderBy.replace("desc", "")));
			} else {
				cr.addOrder(Order.asc(orderBy));
			}
		}
		return cr.list();
	}
}
