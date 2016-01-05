package com.teaonlinestore.service;

import org.apache.log4j.Logger;

import com.teaonlinestore.dao.CoffeDao;
import com.teaonlinestore.dao.CustomerDao;
import com.teaonlinestore.dao.DaoFactory;
import com.teaonlinestore.dao.HibernateDaoFactory;
import com.teaonlinestore.model.Customer;
import com.teaonlinestore.utils.HibernateUtil;

public class CustomerManager implements CustomerManagerInterface {
	private static final Logger LOG = Logger.getLogger(CustomerManager.class);
	private DaoFactory daoFactory;
	private CustomerDao customerDao;

	public CustomerManager() {
		this(new HibernateDaoFactory());
	}

	public CustomerManager(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
		customerDao = daoFactory.createCustomerDao();
	}
	
	@Override
	public Customer getCustomerById(Long customerId) {
		Customer customer = null;
		try {
			HibernateUtil.beginTransaction();
			customer = customerDao.findByID(Customer.class, customerId);
			HibernateUtil.commitTransaction();
		} catch (Exception ex){
			LOG.error("Get Customer by id transaction failed", ex);
		}
		return customer;
	}
	
	@Override
	public Customer getCustomerByEmail(String email) {
		Customer customer = null;
		try {
			HibernateUtil.beginTransaction();
			customer = customerDao.getCustomerByEmail(email);
			HibernateUtil.commitTransaction();
		} catch (Exception ex){
			LOG.error("Get Customer by email transaction failed", ex);
		}
		return customer;
	}
	
	@Override
	public void saveCustomer(Customer customer) {
		try {
			HibernateUtil.beginTransaction();
			customerDao.save(customer);
			HibernateUtil.commitTransaction();
		} catch (Exception ex){
			LOG.error("Save Customer failed", ex);
		}
	}
	
	public DaoFactory getDaoFactory() {
		return daoFactory;
	}

	public void setDaoFactory(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
		customerDao = daoFactory.createCustomerDao();
	}
	
}
