package com.teaonlinestore.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;

import com.teaonlinestore.dao.AttributeDao;
import com.teaonlinestore.dao.DaoFactory;
import com.teaonlinestore.dao.HibernateDaoFactory;
import com.teaonlinestore.model.Attribute;
import com.teaonlinestore.service.AttributeManagerInterface;
import com.teaonlinestore.utils.HibernateUtil;

public class AttributeManager implements AttributeManagerInterface {
	private static final Logger LOG = Logger.getLogger(AttributeManager.class);
	private DaoFactory daoFactory;
	private AttributeDao attributeDao;
	
	public AttributeManager() {
		this(new HibernateDaoFactory());
	}

	public AttributeManager(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
		attributeDao = daoFactory.createAttributeDao();
	}

	public DaoFactory getDaoFactory() {
		return daoFactory;
	}

	public void setDaoFactory(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
		attributeDao = daoFactory.createAttributeDao();
	}
	
	@Override
	public List<Attribute> getAttributesByIds(Collection<String> ids) {
		List<Attribute> attributes = null;
		try {
			HibernateUtil.beginTransaction();
			attributes = attributeDao.getAttributesByIds(ids);
			HibernateUtil.commitTransaction();
		} catch (Exception ex){
			HibernateUtil.rollbackTransaction();
			LOG.error("Get Attributes By Ids transaction failed", ex);
		}
		return attributes != null ? attributes : new ArrayList<Attribute>();
	}

}
