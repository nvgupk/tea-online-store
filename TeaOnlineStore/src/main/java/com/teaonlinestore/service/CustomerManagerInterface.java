package com.teaonlinestore.service;

import com.teaonlinestore.model.Customer;

public interface CustomerManagerInterface {
	public Customer getRegisteredCustomerByEmail(String email);
	public Customer getRegisteredCustomerByEmailAndPassword(String email, String password);
	public Customer getCustomerById(Long customerId);
	public void saveCustomer(Customer customer);
}
