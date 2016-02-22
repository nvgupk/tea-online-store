package com.teaonlinestore.service;

import java.util.Collection;
import java.util.List;

import com.teaonlinestore.model.Attribute;

public interface AttributeManagerInterface {
	public List<Attribute> getAttributesByIds(Collection<String> ids);
}
