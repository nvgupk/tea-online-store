package com.teaonlinestore.dao;

import java.util.List;

import com.teaonlinestore.model.Payment;

public interface PaymentDao extends GenericDao<Payment, Long>{
	public List<Payment> getAllValidPayment();
}
