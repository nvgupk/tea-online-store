package com.teaonlinestore.dao;

public interface DaoFactory {
	CategoryDao createCategoryDao();
	CustomerDao createCustomerDao();
	DeliveryDao createDeliveryDao();
	PaymentDao createPaymentDao();
	ProductDao createProductDao();
	PurchaseDao createPurchaseDao();
	StatusDao createStatusDao();
	TeaDao createTeaDao();
	CoffeDao createCoffeDao();
	MakerDao createMakerDao();
	AttributeDao createAttributeDao();
}
