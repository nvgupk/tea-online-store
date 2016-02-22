package com.teaonlinestore.dao;

import java.util.List;

import com.teaonlinestore.model.Customer;
import com.teaonlinestore.model.Purchase;

public interface PurchaseDao extends GenericDao<Purchase, Long> {
	public List<Purchase> getPurchaseByCustomer(Customer customer);
}
