package com.teaonlinestore.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.persister.entity.AbstractEntityPersister;

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
	
	public List<String> getAttributeNames() {
		AbstractEntityPersister aep = (AbstractEntityPersister) HibernateUtil.getSessionFactory().getClassMetadata(Tea.class);
		String[] propertyNames = aep.getPropertyNames();
		return new ArrayList<String>(Arrays.asList(propertyNames));
	}
	
	public Map<String, List<String>> getAttributeValues(Set<String> attributes) {
		Map<String, List<String>> attributeValues = new HashMap<String, List<String>>();
		Session session = HibernateUtil.getSession();
		for(String attribute : attributes) {
			Query query = session.createQuery("select distinct " + attribute + " from Tea");
			attributeValues.put(attribute, query.list());
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
}
