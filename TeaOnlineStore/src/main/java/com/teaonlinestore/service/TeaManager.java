package com.teaonlinestore.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import com.teaonlinestore.dao.CategoryDao;
import com.teaonlinestore.dao.DaoFactory;
import com.teaonlinestore.dao.HibernateDaoFactory;
import com.teaonlinestore.dao.TeaDao;
import com.teaonlinestore.utils.FileUtil;
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
	
	public Map<String, String> getAttributeNamesUA() {
		List<String> attributes = teaDao.getAttributeNames();
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
	public List<String> getProductKinds() {
		List<String> kinds = new ArrayList<String>();
		try {
			HibernateUtil.beginTransaction();
			kinds = teaDao.getTeaKinds();
			HibernateUtil.commitTransaction();
		} catch (Exception ex){
			LOG.error("Get Tea kinds transaction failed", ex);
		}
		return kinds;
	}
	
	@Override
	public Map<String, List<?>> getAttributeValues(Set<String> attributes) {
		Map<String, List<?>> attributeValues = new HashMap<String, List<?>>();
		try {
			HibernateUtil.beginTransaction();
			attributeValues = teaDao.getAttributeValues(attributes);
			HibernateUtil.commitTransaction();
		} catch (Exception ex) {
			LOG.error("Get Tea's attribute values transaction failed", ex);
		}
		return attributeValues;
	}
	
	public DaoFactory getDaoFactory() {
		return daoFactory;
	}

	public void setDaoFactory(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
		teaDao = daoFactory.createTeaDao();
	}
	
}
