package com.teaonlinestore.dao;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.teaonlinestore.model.Attribute;
import com.teaonlinestore.utils.HibernateUtil;

public class AttributeDaoHibernate extends GenericDaoHibernate<Attribute, String> implements AttributeDao{
	
	@Override
	public List<Attribute> getAllAttribute() {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from Attribute");
		return query.list();
	}
	
	@Override
	public List<Attribute> getAttributesByIds(Collection<String> ids) {
		if(ids == null || ids.isEmpty()) {
			return new ArrayList<Attribute>();
		}
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from Attribute where attrName in(:ids)");
		query.setParameterList("ids",ids);
		return query.list();
	}
}
