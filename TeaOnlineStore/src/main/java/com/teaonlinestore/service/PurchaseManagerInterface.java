package com.teaonlinestore.service;

import java.util.List;

import com.teaonlinestore.model.Customer;
import com.teaonlinestore.model.Purchase;

public interface PurchaseManagerInterface {
	/**
	 * Thread safe method which store the customer's Purchase in the database.
	 * Method returns true if the operation has finished successfully, otherwise returns false.
	 * If there are not enough ordered goods, method also returns false
	 * @param purchase Purchase object which contains information about customer's purchase
	 * @return true if the operation has finished successfully, otherwise returns false
	 */
	public Boolean makePurchase(Purchase purchase);
	public List<Purchase> getPurchaseByCustomer(Customer customer);
}
