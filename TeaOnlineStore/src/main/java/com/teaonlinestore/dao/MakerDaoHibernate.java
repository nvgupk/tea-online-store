package com.teaonlinestore.dao;

import org.hibernate.Query;
import org.hibernate.Session;

import com.teaonlinestore.model.Maker;
import com.teaonlinestore.utils.HibernateUtil;

public class MakerDaoHibernate extends GenericDaoHibernate<Maker, Long> implements MakerDao {
	@Override
	public Maker getMakerByName(String name) {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from Maker where name = :name");
		query.setParameter("name", name);
		return (Maker) query.list().get(0);
	}
}
