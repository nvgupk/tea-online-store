package com.teaonlinestore.service;

import java.util.List;

import com.teaonlinestore.model.Delivery;

public interface DeliveryManagerInterface {
	public List<Delivery> getAllValidDelivery();
	public Delivery getDeliveryById(Long deliveryId);
}
