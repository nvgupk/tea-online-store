package com.teaonlinestore.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.teaonlinestore.model.Customer;
import com.teaonlinestore.utils.HibernateUtil;

public class CustomerDaoHibernate extends GenericDaoHibernate<Customer, Long> implements CustomerDao {
	public Customer getCustomerByEmail(String email) {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from Customer where email = :email");
		query.setParameter("email", email);
		List<Customer> customers = query.list();
		Customer customer = null;
		if(!customers.isEmpty()){
			customer = customers.get(0);
		}
		return customer;
	}
}