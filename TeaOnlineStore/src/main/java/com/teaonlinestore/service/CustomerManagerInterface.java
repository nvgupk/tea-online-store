package com.teaonlinestore.service;

import com.teaonlinestore.model.Customer;

public interface CustomerManagerInterface {
	public Customer getCustomerByEmail(String email);
	public Customer getCustomerById(Long customerId);
	public void saveCustomer(Customer customer);
}
