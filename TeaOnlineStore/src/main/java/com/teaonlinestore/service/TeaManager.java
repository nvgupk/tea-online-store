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

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import com.teaonlinestore.dao.CategoryDao;
import com.teaonlinestore.dao.DaoFactory;
import com.teaonlinestore.dao.HibernateDaoFactory;
import com.teaonlinestore.dao.TeaDao;
import com.teaonlinestore.utils.FileUtil;
import com.teaonlinestore.utils.HibernateUtil;

public class TeaManager extends ProductManager implements TeaManagerInterface {
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
	
	public Map<String, String> getColumnNamesUA() {
		List<String> columns = teaDao.getColumnNames();
		Map<String, String> columnsUA = new HashMap<String, String>();
		Map<String, String> properties = FileUtil.getProperties();
		for(String column : columns) {
			String columnUA = properties.get(column);
			if(columnUA != null) {
				columnsUA.put(column, columnUA);
			}
		}
		return columnsUA;
	}
	
}
