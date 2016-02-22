package com.teaonlinestore.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.teaonlinestore.dao.DaoFactory;
import com.teaonlinestore.dao.DeliveryDao;
import com.teaonlinestore.dao.HibernateDaoFactory;
import com.teaonlinestore.model.Delivery;
import com.teaonlinestore.utils.HibernateUtil;

public class DeliveryManager implements DeliveryManagerInterface {
	private static final Logger LOG = Logger.getLogger(DeliveryManager.class);
	private DaoFactory daoFactory;
	private DeliveryDao deliveryDao;
	
	public DeliveryManager() {
		this(new HibernateDaoFactory());
	}

	public DeliveryManager(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
		deliveryDao = daoFactory.createDeliveryDao();
	}
	
	public DaoFactory getDaoFactory() {
		return daoFactory;
	}

	public void setDaoFactory(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
		deliveryDao = daoFactory.createDeliveryDao();
	}
	
	@Override
	public List<Delivery> getAllValidDelivery() {
		List<Delivery> deliveries = null;
		try {
			HibernateUtil.beginTransaction();
			deliveries = deliveryDao.getAllValidDelivery();
			HibernateUtil.commitTransaction();
		} catch(Exception ex) {
			HibernateUtil.rollbackTransaction();
			LOG.error("Get all Deliveries transaction failed", ex);
		}
		return deliveries != null ? deliveries : new ArrayList<Delivery>();
	}
	
	@Override
	public Delivery getDeliveryById(Long deliveryId) {
		Delivery delivery = null;
		try {
			HibernateUtil.beginTransaction();
			delivery = deliveryDao.findByID(Delivery.class, deliveryId);
			HibernateUtil.commitTransaction();
		} catch(Exception ex) {
			HibernateUtil.rollbackTransaction();
			LOG.error("Get Delivery by Id transaction failed", ex);
		}
		return delivery;
	}
}
