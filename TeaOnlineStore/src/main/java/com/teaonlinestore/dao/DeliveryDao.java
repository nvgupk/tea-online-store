package com.teaonlinestore.dao;


import java.util.List;

import com.teaonlinestore.model.Delivery;

public interface DeliveryDao extends GenericDao<Delivery, Long>{
	public List<Delivery> getAllValidDelivery();
}
