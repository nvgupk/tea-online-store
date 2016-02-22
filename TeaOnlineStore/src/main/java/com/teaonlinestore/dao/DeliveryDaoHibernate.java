package com.teaonlinestore.dao;

import java.util.List;



import org.hibernate.Query;
import org.hibernate.Session;

import com.teaonlinestore.model.Delivery;
import com.teaonlinestore.utils.HibernateUtil;

public class DeliveryDaoHibernate extends GenericDaoHibernate<Delivery, Long> implements DeliveryDao {
	@Override
	public List<Delivery> getAllValidDelivery() {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from Delivery where visible = :visible");
		query.setBoolean("visible", true);
		return query.list();
	}
}
