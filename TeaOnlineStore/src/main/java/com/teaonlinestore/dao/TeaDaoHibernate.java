package com.teaonlinestore.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.persister.entity.AbstractEntityPersister;

import com.teaonlinestore.model.Tea;
import com.teaonlinestore.utils.HibernateUtil;

public class TeaDaoHibernate extends GenericDaoHibernate<Tea, Long> implements TeaDao {
	public List<String> getAllKindOfTea() {
		List<String> kinds = new ArrayList<String>();
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("select distinct kind from tea");
		kinds = query.list();
		return kinds;
	}
	
	public List<String> getColumnNames() {
		AbstractEntityPersister aep = (AbstractEntityPersister) HibernateUtil.getSessionFactory().getClassMetadata(Tea.class);
		String[] propertyNames = aep.getPropertyNames();
		List<String> columns = new ArrayList<String>();
		for (String property : propertyNames) {
			String column = aep.getPropertyColumnNames(property)[0];
			columns.add(column);
		}
		return columns;
	}
}
