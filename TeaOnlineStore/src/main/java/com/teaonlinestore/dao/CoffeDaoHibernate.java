package com.teaonlinestore.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.persister.entity.AbstractEntityPersister;

import com.teaonlinestore.model.Coffe;
import com.teaonlinestore.model.Tea;
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
	
	public List<String> getAttributeNames() {
		AbstractEntityPersister aep = (AbstractEntityPersister) HibernateUtil.getSessionFactory().getClassMetadata(Coffe.class);
		String[] propertyNames = aep.getPropertyNames();
		return new ArrayList<String>(Arrays.asList(propertyNames));
	}
	
	@Override
	public Map<String, List<?>> getAttributeValues(Set<String> attributes) {
		Map<String, List<?>> attributeValues = new HashMap<String, List<?>>();
		Session session = HibernateUtil.getSession();
		for(String attribute : attributes) {
			Query query = session.createQuery("select distinct " + attribute + " from Coffe");
			attributeValues.put(attribute, query.list());
		}
		return attributeValues;
	}
}
