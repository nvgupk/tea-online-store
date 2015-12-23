package com.teaonlinestore.dao;

import java.util.List;

import com.teaonlinestore.model.Tea;

public interface TeaDao extends GenericDao<Tea, Long> {
	public List<String> getAllKindOfTea();
	public List<String>  getColumnNames();
}
