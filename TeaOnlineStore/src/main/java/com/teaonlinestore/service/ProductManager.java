package com.teaonlinestore.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.teaonlinestore.dao.DaoFactory;
import com.teaonlinestore.dao.HibernateDaoFactory;
import com.teaonlinestore.dao.ProductDao;
import com.teaonlinestore.model.Category;
import com.teaonlinestore.utils.HibernateUtil;

public class ProductManager implements ProductManagerInterface {
	private static final Logger LOG = Logger.getLogger(ProductManager.class);
	private DaoFactory daoFactory;
	private ProductDao productDao;
	
	public ProductManager() {
		this(new HibernateDaoFactory());
	}

	public ProductManager(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
		productDao = daoFactory.createProductDao();
	}
	
	public List<String> getProductKindsByCategory(Category category) {
		List<String> kinds = new ArrayList<String>();
		try {
			HibernateUtil.beginTransaction();
			kinds = productDao.getProductKindsByCategory(category);
			HibernateUtil.commitTransaction();
		} catch (Exception ex){
			LOG.error("Get kinds by Category transaction field", ex);
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
