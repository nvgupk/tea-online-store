package com.teaonlinestore.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.teaonlinestore.dao.DaoFactory;
import com.teaonlinestore.dao.HibernateDaoFactory;
import com.teaonlinestore.dao.ProductDao;
import com.teaonlinestore.model.Product;
import com.teaonlinestore.utils.HibernateUtil;

public class ProductManager implements ProductManagerInterface{
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
	
	public DaoFactory getDaoFactory() {
		return daoFactory;
	}

	public void setDaoFactory(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
		productDao = daoFactory.createProductDao();
	}
	
	@Override
	public Product getProductById(Long productId) {
		Product product = null;
		try {
			HibernateUtil.beginTransaction();
			product = productDao.findByID(Product.class, productId);
			HibernateUtil.commitTransaction();
		} catch (Exception ex) {
			HibernateUtil.rollbackTransaction();
			LOG.error("Get Product by id transaction failed", ex);
		}
		return product;
	}
	
	@Override
	public List<Product> getMostPopularProducts(int selectionsSize) {
		List<Product> products = null;
		try {
			HibernateUtil.beginTransaction();
			products = productDao.getMostPopularProducts(selectionsSize);
			HibernateUtil.commitTransaction();
		} catch (Exception ex) {
			HibernateUtil.rollbackTransaction();
			LOG.error("Get most popular product transaction failed", ex);
		}
		return products != null ? products : new ArrayList<Product>();
	}
	
	@Override
	public List<Product> getProductsByPartialName(String partialName) {
		List<Product> products = null;
		try {
			HibernateUtil.beginTransaction();
			products = productDao.getProductsByPartialName(partialName);
			HibernateUtil.commitTransaction();
		} catch (Exception ex) {
			HibernateUtil.rollbackTransaction();
			LOG.error("Get Products by partial name transaction failed", ex);
		}
		return products != null ? products : new ArrayList<Product>();
	}
	
	@Override
	public JSONObject parseProductToJSONObject(Product product) {
		JSONObject json = new JSONObject();
		json.put("productId", product.getProductId());
		json.put("name", product.getName());
		json.put("price", product.getPrice());
		json.put("image", product.getImage());
		json.put("categoryId", product.getCategory().getCategoryId());
		return json;
	}
	
	@Override
	public JSONArray parseProductsToJSONArray(Collection<Product> products) {
		JSONArray jsonArray = new JSONArray();
		for (Product product : products) {
			JSONObject json = parseProductToJSONObject(product);
			jsonArray.add(json);
		}
		return jsonArray;
	}
}
