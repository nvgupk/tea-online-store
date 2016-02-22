package com.teaonlinestore.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.teaonlinestore.model.Payment;
import com.teaonlinestore.utils.HibernateUtil;

public class PaymentDaoHibernate extends GenericDaoHibernate<Payment, Long> implements PaymentDao {
	@Override
	public List<Payment> getAllValidPayment() {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from Payment where visible = :visible");
		query.setBoolean("visible", true);
		return query.list();
	}
}
