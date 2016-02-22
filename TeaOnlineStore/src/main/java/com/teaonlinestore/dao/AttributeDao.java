package com.teaonlinestore.dao;

import java.util.Collection;
import java.util.List;

import com.teaonlinestore.model.Attribute;

public interface AttributeDao extends GenericDao<Attribute, String>{
	public List<Attribute> getAllAttribute();
	public List<Attribute> getAttributesByIds(Collection<String> ids);
}
