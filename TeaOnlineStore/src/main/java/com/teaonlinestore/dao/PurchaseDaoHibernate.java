package com.teaonlinestore.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.teaonlinestore.model.Customer;
import com.teaonlinestore.model.Purchase;
import com.teaonlinestore.utils.HibernateUtil;

public class PurchaseDaoHibernate extends GenericDaoHibernate<Purchase, Long> implements PurchaseDao {
	@Override
	public List<Purchase> getPurchaseByCustomer(Customer customer) {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from Purchase where customer = :customer");
		query.setParameter("customer", customer);
		return query.list();
	}
}
