package com.teaonlinestore.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.teaonlinestore.dao.DaoFactory;
import com.teaonlinestore.dao.HibernateDaoFactory;
import com.teaonlinestore.dao.TeaDao;
import com.teaonlinestore.model.Attribute;
import com.teaonlinestore.model.Product;
import com.teaonlinestore.model.Tea;
import com.teaonlinestore.utils.HibernateUtil;

public class TeaManager implements TeaManagerInterface {
	private static final Logger LOG = Logger.getLogger(TeaManager.class);
	private DaoFactory daoFactory;
	private TeaDao teaDao;
	
	public TeaManager() {
		this(new HibernateDaoFactory());
	}

	public TeaManager(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
		teaDao = daoFactory.createTeaDao();
	}
	
	public DaoFactory getDaoFactory() {
		return daoFactory;
	}

	public void setDaoFactory(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
		teaDao = daoFactory.createTeaDao();
	}
	
	@Override
	public List<Tea> getProductsByAttributes(Map<String, List<String>> attributeValues, Double minPrice, Double maxPrice, String orderBy) {
		List<Tea> products = null;
		try {
			HibernateUtil.beginTransaction();
			products = teaDao.getEntitysByAttributes(attributeValues, minPrice, maxPrice, orderBy);
			HibernateUtil.commitTransaction();
		} catch (Exception ex){
			HibernateUtil.rollbackTransaction();
			LOG.error("Get Tea by several attributes transaction failed", ex);
		}
		return products != null ? products : new ArrayList<Tea>(0);
	}
	
	@Override
	public List<? extends Product> getProductsByAttributes(Map<String, List<String>> attributeValues, String orderBy) {
		return getProductsByAttributes(attributeValues, null, null, orderBy);
	}
	
	public Set<String> getAttributeNames() {
		return teaDao.getAttributeNames(Tea.class);
	}
	
	@Override
	public List<String> getProductKinds() {
		List<String> kinds = null;
		try {
			HibernateUtil.beginTransaction();
			kinds = teaDao.getTeaKinds();
			HibernateUtil.commitTransaction();
		} catch (Exception ex){
			HibernateUtil.rollbackTransaction();
			LOG.error("Get Tea kinds transaction failed", ex);
		}
		return kinds != null ? kinds : new ArrayList<String>(0);
	}
	
	@Override
	public Map<Attribute, List<String>> getAttributeValues(Collection<Attribute> attributes) {
		Map<Attribute, List<String>> attributeValues = null;
		try {
			HibernateUtil.beginTransaction();
			attributeValues = teaDao.getAttributeValues(attributes);
			HibernateUtil.commitTransaction();
		} catch (Exception ex) {
			HibernateUtil.rollbackTransaction();
			LOG.error("Get Tea's attribute values transaction failed", ex);
		}
		return attributeValues != null ? attributeValues : new HashMap<Attribute, List<String>>(0);
	}
	
	@Override
	public Map<Attribute, String> getAttributeValues(Collection<Attribute> attributes, Product product) {
		Map<Attribute, String> attributeValues = null;
		try {
			HibernateUtil.beginTransaction();
			attributeValues = teaDao.getAttributeValues(attributes, product);
			HibernateUtil.commitTransaction();
		} catch (Exception ex) {
			HibernateUtil.rollbackTransaction();
			LOG.error("Get Tea's attribute values transaction failed", ex);
		}
		return attributeValues != null ? attributeValues : new HashMap<Attribute, String>();
	}
	
	@Override
	public Double getProductMaxPrice() {
		Double maxPrice = null;
		try {
			HibernateUtil.beginTransaction();
			maxPrice = teaDao.getTeaMaxPrice();
			HibernateUtil.commitTransaction();
		} catch (Exception ex) {
			HibernateUtil.rollbackTransaction();
			LOG.error("Get Tea's max price transaction failed", ex);
		}
		return maxPrice;
	}
	
	@Override
	public Double getProductMinPrice() {
		Double minPrice = null;
		try {
			HibernateUtil.beginTransaction();
			minPrice = teaDao.getTeaMinPrice();
			HibernateUtil.commitTransaction();
		} catch (Exception ex) {
			HibernateUtil.rollbackTransaction();
			LOG.error("Get Tea's min price transaction failed", ex);
		}
		return minPrice;
	}
	
	@Override
	public Product getProductById(Long id) {
		Product product = null;
		try {
			HibernateUtil.beginTransaction();
			product = teaDao.findByID(Tea.class, id);
			HibernateUtil.commitTransaction();
		} catch (Exception ex){
			HibernateUtil.rollbackTransaction();
			LOG.error("Get Tea by id transaction failed", ex);
		}
		return product;
	}
}
