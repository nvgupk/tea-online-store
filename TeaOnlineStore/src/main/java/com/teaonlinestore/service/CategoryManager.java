package com.teaonlinestore.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.teaonlinestore.dao.CategoryDao;
import com.teaonlinestore.dao.DaoFactory;
import com.teaonlinestore.dao.HibernateDaoFactory;
import com.teaonlinestore.model.Category;
import com.teaonlinestore.utils.HibernateUtil;

public class CategoryManager implements CategoryManagerInterface {
	private static final Logger LOG = Logger.getLogger(CategoryManager.class);
	private DaoFactory daoFactory;
	private CategoryDao categoryDao;
	
	public CategoryManager() {
		this(new HibernateDaoFactory());
	}

	public CategoryManager(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
		categoryDao = daoFactory.createCategoryDao();
	}

	public DaoFactory getDaoFactory() {
		return daoFactory;
	}

	public void setDaoFactory(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
		categoryDao = daoFactory.createCategoryDao();
	}

	public List<Category> getAllCategory() {
		List<Category> categories = null;
		try {
			HibernateUtil.beginTransaction();
			categories = categoryDao.getAllCategory();
			HibernateUtil.commitTransaction();
		} catch(Exception ex) {
			HibernateUtil.rollbackTransaction();
			LOG.error("Get all Category transaction failed", ex);
		}
		return categories != null ? categories : new ArrayList<Category>();
	}
	
	public List<Category> getCategoryByVisible(boolean visible) {
		List<Category> categories = null;
		try {
			HibernateUtil.beginTransaction();
			categories = categoryDao.getCategoryByVisible(visible);
			HibernateUtil.commitTransaction();
		} catch(Exception ex) {
			HibernateUtil.rollbackTransaction();
			LOG.error("Get all Category transaction failed", ex);
		}
		return categories != null ? categories : new ArrayList<Category>();
	}
	
	public Category getById(Long id) {
		Category category = null;
		try {
			HibernateUtil.beginTransaction();
			category = categoryDao.findByID(Category.class, id);
			HibernateUtil.commitTransaction();
		} catch(Exception ex) {
			HibernateUtil.rollbackTransaction();
			LOG.error("Get Category by id transaction failed", ex);
		}
		return category;
	}
	
}
