package com.teaonlinestore.dao;

import com.teaonlinestore.model.Customer;

public interface CustomerDao extends GenericDao<Customer, Long>{
	public Customer getRegisteredCustomerByEmail(String email);
	public Customer getRegisteredCustomerByEmailAndPassword(String email, String password);
}
