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
import com.teaonlinestore.model.Coffe;
import com.teaonlinestore.model.Product;
import com.teaonlinestore.utils.HibernateUtil;

public class CoffeDaoHibernate extends GenericDaoHibernate<Coffe, Long> implements CoffeDao {
	
	@Override
	public List<String> getCoffeKinds() {
		List<String> kinds = new ArrayList<String>();
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("select distinct kind from Coffe");
		kinds = query.list();
		return kinds;
	}
	
	@Override
	public Map<Attribute, List<String>> getAttributeValues(Collection<Attribute> attributes) {
		Map<Attribute, List<String>> attributeValues = new HashMap<Attribute, List<String>>();
		Session session = HibernateUtil.getSession();
		for(Attribute attribute : attributes) {
			Query query = session.createQuery("select distinct " + attribute.getAttrName() + " from Coffe");
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
			Query query = session.createQuery("select distinct " + attribute.getAttrName() + " from Coffe where productId=:productId");
			query.setParameter("productId", product.getProductId());
			List<String> selection = query.list();
			if(!selection.isEmpty()){
				attributeValues.put(attribute, (String) query.list().get(0));
			}
		}
		return attributeValues;
	}
	
	@Override
	public Double getCoffeMaxPrice() {
		Session session = HibernateUtil.getSession();
		Criteria cr = session.createCriteria(Coffe.class);
		cr.setProjection(Projections.max("price"));
		return (Double) cr.list().get(0);
	}
	
	@Override
	public Double getCoffeMinPrice() {
		Session session = HibernateUtil.getSession();
		Criteria cr = session.createCriteria(Coffe.class);
		cr.setProjection(Projections.min("price"));
		return (Double) cr.list().get(0);
	}
	
	@Override
	public List<Coffe> getEntitysByAttributes(Map<String, List<String>> attributeValues, Double minPrice, Double maxPrice, String orderBy) {
		Session session = HibernateUtil.getSession();
		Criteria cr = session.createCriteria(Coffe.class);
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
