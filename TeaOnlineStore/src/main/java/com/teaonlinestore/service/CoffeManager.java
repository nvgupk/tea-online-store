package com.teaonlinestore.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.teaonlinestore.dao.CoffeDao;
import com.teaonlinestore.dao.DaoFactory;
import com.teaonlinestore.dao.HibernateDaoFactory;
import com.teaonlinestore.dao.TeaDao;
import com.teaonlinestore.model.Coffe;
import com.teaonlinestore.model.Product;
import com.teaonlinestore.model.Tea;
import com.teaonlinestore.utils.FileUtil;
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
	
	public List<Coffe> getProductsByAttributes(Map<String, List<String>> attributeValues, Double minPrice, Double maxPrice) {
		List<Coffe> products = new ArrayList<Coffe>();
		try {
			HibernateUtil.beginTransaction();
			products = coffeDao.getEntitysByAttributes(attributeValues, minPrice, maxPrice, Coffe.class);
			HibernateUtil.commitTransaction();
		} catch (Exception ex){
			LOG.error("Get Сoffe by several attributes transaction failed", ex);
		}
		return products;
	}
	
	@Override
	public List<? extends Product> getProductsByAttributes(Map<String, List<String>> attributeValues) {
		return getProductsByAttributes(attributeValues, null, null);
	}
	
	@Override
	public Double getProductMaxPrice() {
		Double maxPrice = null;
		try {
			HibernateUtil.beginTransaction();
			maxPrice = coffeDao.getCoffeMaxPrice();
			HibernateUtil.commitTransaction();
		} catch (Exception ex) {
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
			LOG.error("Get Coffe's min price transaction failed", ex);
		}
		return minPrice;
	}
	
	public Map<String, String> getAttributeNamesUA() {
		List<String> attributes = coffeDao.getAttributeNames();
		Map<String, String> attributesUA = new HashMap<String, String>();
		Map<String, String> properties = FileUtil.getProperties();
		for(String column : attributes) {
			String columnUA = properties.get(column);
			if(columnUA != null) {
				attributesUA.put(column, columnUA);
			}
		}
		return attributesUA;
	}
	
	@Override
	public Map<String, List<String>> getAttributeValues(Set<String> attributes) {
		Map<String, List<String>> attributeValues = new HashMap<String, List<String>>();
		try {
			HibernateUtil.beginTransaction();
			attributeValues = coffeDao.getAttributeValues(attributes);
			HibernateUtil.commitTransaction();
		} catch (Exception ex){
			LOG.error("Get Coffe's attribute values transaction failed", ex);
		}
		return attributeValues;
	}
	
	@Override
	public List<String> getProductKinds() {
		List<String> kinds = new ArrayList<String>();
		try {
			HibernateUtil.beginTransaction();
			kinds = coffeDao.getCoffeKinds();
			HibernateUtil.commitTransaction();
		} catch (Exception ex){
			LOG.error("Get Coffe kinds transaction failed", ex);
		}
		return kinds;
	}
	
	public DaoFactory getDaoFactory() {
		return daoFactory;
	}

	public void setDaoFactory(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
		coffeDao = daoFactory.createCoffeDao();
	}
}
