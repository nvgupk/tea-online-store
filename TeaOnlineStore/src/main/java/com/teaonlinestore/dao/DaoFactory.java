package com.teaonlinestore.dao;


public interface DaoFactory {
	CategoryDao createCategoryDao();
	CustomerDao createCustomerDao();
	DeliveryDao createDeliveryDao();
	PeymentDao createPeymentDao();
	ProductDao createProductDao();
	PurchaseDao createPurchaseDao();
	StatusDao createStatusDao();
}
