package com.teaonlinestore.service;

import org.apache.log4j.Logger;

import com.teaonlinestore.dao.DaoFactory;
import com.teaonlinestore.dao.HibernateDaoFactory;
import com.teaonlinestore.dao.MakerDao;
import com.teaonlinestore.model.Maker;
import com.teaonlinestore.utils.HibernateUtil;

public class MakerManager implements MakerManagerInterface {
	private static final Logger LOG = Logger.getLogger(MakerManager.class);
	private DaoFactory daoFactory;
	private MakerDao makerDao;
		
	public MakerManager() {
		this(new HibernateDaoFactory());
	}

	public MakerManager(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
		makerDao = daoFactory.createMakerDao();
	}

	public DaoFactory getDaoFactory() {
		return daoFactory;
	}

	public void setDaoFactory(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
		makerDao = daoFactory.createMakerDao();
	}

	@Override
	public Maker getMakerByName(String name) {
		Maker maker = null;
		try {
			HibernateUtil.beginTransaction();
			maker = makerDao.getMakerByName(name);
			HibernateUtil.commitTransaction();
		} catch (Exception ex){
			HibernateUtil.rollbackTransaction();
			LOG.error("Get Maker by name transaction failed", ex);
		}
		return maker;
	}

}
