package com.teaonlinestore.dao;

public class HibernateDaoFactory implements DaoFactory {

	public CategoryDao createCategoryDao() {
		return new CategoryDaoHibernate();
	}

	public CustomerDao createCustomerDao() {
		return new CustomerDaoHibernate();
	}

	public DeliveryDao createDeliveryDao() {
		return new DeliveryDaoHibernate();
	}

	public PeymentDao createPeymentDao() {
		return new PeymentDaoHibernate();
	}

	public ProductDao createProductDao() {
		return new ProductDaoHibernate();
	}

	public PurchaseDao createPurchaseDao() {
		return new PurchaseDaoHibernate();
	}

	public StatusDao createStatusDao() {
		return new StatusDaoHibernate();
	}
	
}
