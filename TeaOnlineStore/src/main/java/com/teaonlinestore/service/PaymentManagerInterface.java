package com.teaonlinestore.service;

import java.util.List;

import com.teaonlinestore.model.Payment;

public interface PaymentManagerInterface {
	public List<Payment> getAllValidPayment();
	public Payment getPaymentById(Long paymentId);
}
