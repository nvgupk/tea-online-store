package com.teaonlinestore.dao;

import com.teaonlinestore.model.Customer;

public interface CustomerDao extends GenericDao<Customer, Long>{
	public Customer getCustomerByEmail(String email);
}
