package com.teaonlinestore.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.teaonlinestore.dao.DaoFactory;
import com.teaonlinestore.dao.HibernateDaoFactory;
import com.teaonlinestore.dao.ProductDao;
import com.teaonlinestore.model.Category;
import com.teaonlinestore.model.Product;
import com.teaonlinestore.utils.HibernateUtil;

public class ProductManager implements ProductManagerInterface {
	private static final Logger LOG = Logger.getLogger(ProductManager.class);
	private DaoFactory daoFactory;
	private ProductDao productDao;
	
	@Override
	public List<? extends Product> getProductsByAttributes(
			Map<String, List<String>> attributeValues, Double minPrice,
			Double maxPrice) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<? extends Product> getProductsByAttributes(
			Map<String, List<String>> attributeValues) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Double getProductMaxPrice() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Double getProductMinPrice() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Map<String, List<String>> getAttributeValues(Set<String> attributes) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Map<String, String> getAttributeNamesUA() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ProductManager() {
		this(new HibernateDaoFactory());
	}

	public ProductManager(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
		productDao = daoFactory.createProductDao();
	}
	
	public List<String> getProductKinds() {
		List<String> kinds = new ArrayList<String>();
		try {
			HibernateUtil.beginTransaction();
			//kinds = productDao.getProductKindsByCategory(category);
			HibernateUtil.commitTransaction();
		} catch (Exception ex){
			LOG.error("Get kinds by Category transaction failed", ex);
		}
		return kinds;
	}
	
	public DaoFactory getDaoFactory() {
		return daoFactory;
	}

	public void setDaoFactory(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
		productDao = daoFactory.createProductDao();
	}
		
}
