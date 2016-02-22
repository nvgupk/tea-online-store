package com.teaonlinestore.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.teaonlinestore.dao.CoffeDao;
import com.teaonlinestore.dao.DaoFactory;
import com.teaonlinestore.dao.HibernateDaoFactory;
import com.teaonlinestore.model.Attribute;
import com.teaonlinestore.model.Coffe;
import com.teaonlinestore.model.Product;
import com.teaonlinestore.utils.HibernateUtil;

public class CoffeManager implements CoffeManagerInterface {
	private static final Logger LOG = Logger.getLogger(CoffeManager.class);
	private DaoFactory daoFactory;
	private CoffeDao coffeDao;
	
	public CoffeManager() {
		this(new HibernateDaoFactory());
	}

	public CoffeManager(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
		coffeDao = daoFactory.createCoffeDao();
	}
	
	public DaoFactory getDaoFactory() {
		return daoFactory;
	}

	public void setDaoFactory(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
		coffeDao = daoFactory.createCoffeDao();
	}
	
	public List<Coffe> getProductsByAttributes(Map<String, List<String>> attributeValues, Double minPrice, Double maxPrice, String orderBy) {
		List<Coffe> products = null;
		try {
			HibernateUtil.beginTransaction();
			products = coffeDao.getEntitysByAttributes(attributeValues, minPrice, maxPrice, orderBy);
			HibernateUtil.commitTransaction();
		} catch (Exception ex){
			HibernateUtil.rollbackTransaction();
			LOG.error("Get Сoffe by several attributes transaction failed", ex);
		}
		return products != null ? products : new ArrayList<Coffe>();
	}
	
	@Override
	public List<? extends Product> getProductsByAttributes(Map<String, List<String>> attributeValues, String orderBy) {
		return getProductsByAttributes(attributeValues, null, null, orderBy);
	}
	
	@Override
	public Double getProductMaxPrice() {
		Double maxPrice = null;
		try {
			HibernateUtil.beginTransaction();
			maxPrice = coffeDao.getCoffeMaxPrice();
			HibernateUtil.commitTransaction();
		} catch (Exception ex) {
			HibernateUtil.rollbackTransaction();
			LOG.error("Get Coffe's max price transaction failed", ex);
		}
		return maxPrice;
	}
	
	@Override
	public Double getProductMinPrice() {
		Double minPrice = null;
		try {
			HibernateUtil.beginTransaction();
			minPrice = coffeDao.getCoffeMinPrice();
			HibernateUtil.commitTransaction();
		} catch (Exception ex) {
			HibernateUtil.rollbackTransaction();
			LOG.error("Get Coffe's min price transaction failed", ex);
		}
		return minPrice;
	}
	
	public Set<String> getAttributeNames() {
		return coffeDao.getAttributeNames(Coffe.class);
	}
	
	@Override
	public Map<Attribute, List<String>> getAttributeValues(Collection<Attribute> attributes) {
		Map<Attribute, List<String>> attributeValues = null;
		try {
			HibernateUtil.beginTransaction();
			attributeValues = coffeDao.getAttributeValues(attributes);
			HibernateUtil.commitTransaction();
		} catch (Exception ex){
			HibernateUtil.rollbackTransaction();
			LOG.error("Get Coffe's attribute values transaction failed", ex);
		}
		return attributeValues != null ? attributeValues : new HashMap<Attribute, List<String>>();
	}
	
	@Override
	public Map<Attribute, String> getAttributeValues(Collection<Attribute> attributes, Product product) {
		Map<Attribute, String> attributeValues = null;
		try {
			HibernateUtil.beginTransaction();
			attributeValues = coffeDao.getAttributeValues(attributes, product);
			HibernateUtil.commitTransaction();
		} catch (Exception ex){
			HibernateUtil.rollbackTransaction();
			LOG.error("Get Coffe's attribute values transaction failed", ex);
		}
		return attributeValues != null ? attributeValues : new HashMap<Attribute, String>();
	}
	
	@Override
	public List<String> getProductKinds() {
		List<String> kinds = null;
		try {
			HibernateUtil.beginTransaction();
			kinds = coffeDao.getCoffeKinds();
			HibernateUtil.commitTransaction();
		} catch (Exception ex){
			HibernateUtil.rollbackTransaction();
			LOG.error("Get Coffe kinds transaction failed", ex);
		}
		return kinds != null ? kinds : new ArrayList<String>();
	}
	
	@Override
	public Product getProductById(Long id) {
		Product product = null;
		try {
			HibernateUtil.beginTransaction();
			product = coffeDao.findByID(Coffe.class, id);
			HibernateUtil.commitTransaction();
		} catch (Exception ex){
			HibernateUtil.rollbackTransaction();
			LOG.error("Get Coffe by id transaction failed", ex);
		}
		return product;
	}
}
