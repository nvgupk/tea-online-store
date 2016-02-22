package com.teaonlinestore.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.teaonlinestore.dao.DaoFactory;
import com.teaonlinestore.dao.HibernateDaoFactory;
import com.teaonlinestore.dao.PaymentDao;
import com.teaonlinestore.model.Payment;
import com.teaonlinestore.utils.HibernateUtil;

public class PaymentManager implements PaymentManagerInterface {
	private static final Logger LOG = Logger.getLogger(PaymentManager.class);
	private DaoFactory daoFactory;
	private PaymentDao paymentDao;
	
	public PaymentManager() {
		this(new HibernateDaoFactory());
	}

	public PaymentManager(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
		paymentDao = daoFactory.createPaymentDao();
	}
	
	public DaoFactory getDaoFactory() {
		return daoFactory;
	}

	public void setDaoFactory(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
		paymentDao = daoFactory.createPaymentDao();
	}
	
	@Override
	public List<Payment> getAllValidPayment() {
		List<Payment> payments = null;
		try {
			HibernateUtil.beginTransaction();
			payments = paymentDao.getAllValidPayment();
			HibernateUtil.commitTransaction();
		} catch(Exception ex) {
			HibernateUtil.rollbackTransaction();
			LOG.error("Get all Payments transaction failed", ex);
		}
		return payments != null ? payments : new ArrayList<Payment>();
	}
	
	@Override
	public Payment getPaymentById(Long paymentId) {
		Payment payment = null;
		try {
			HibernateUtil.beginTransaction();
			payment = paymentDao.findByID(Payment.class, paymentId);
			HibernateUtil.commitTransaction();
		} catch(Exception ex) {
			HibernateUtil.rollbackTransaction();
			LOG.error("Get Payment by Id transaction failed", ex);
		}
		return payment;
	}

}
