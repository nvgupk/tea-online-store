package com.teaonlinestore.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.teaonlinestore.dao.DaoFactory;
import com.teaonlinestore.dao.HibernateDaoFactory;
import com.teaonlinestore.dao.ProductDao;
import com.teaonlinestore.dao.PurchaseDao;
import com.teaonlinestore.model.Customer;
import com.teaonlinestore.model.Product;
import com.teaonlinestore.model.Purchase;
import com.teaonlinestore.model.PurchaseProduct;
import com.teaonlinestore.utils.HibernateUtil;

public class PurchaseManager implements PurchaseManagerInterface {
	private static final Logger LOG = Logger.getLogger(PurchaseManager.class);
	private DaoFactory daoFactory;
	private PurchaseDao purchaseDao;
	
	public PurchaseManager() {
		this(new HibernateDaoFactory());
	}

	public PurchaseManager(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
		purchaseDao = daoFactory.createPurchaseDao();
	}
	
	public DaoFactory getDaoFactory() {
		return daoFactory;
	}

	public void setDaoFactory(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
		purchaseDao = daoFactory.createPurchaseDao();
	}
	
	@Override
	public Boolean makePurchase(Purchase purchase) {
		Boolean successfulOperation = true;
		try {
			synchronized (HibernateUtil.getSessionFactory()) {
				HibernateUtil.beginTransaction();
				ProductDao productDao = new HibernateDaoFactory().createProductDao();
				for(PurchaseProduct purchaseProduct : purchase.getPurchaseProducts()){
					Long productId = purchaseProduct.getProduct().getProductId();
					Product product = productDao.findByID(Product.class, productId);
					Integer quantity = purchaseProduct.getQuantity();
					if (product.getQuentity() > (2 + quantity)) {
						product.setQuentity(product.getQuentity() - quantity);
						productDao.update(product);
					} else {
						successfulOperation = false;
						break;
					}
				}
				if(successfulOperation) {
					purchaseDao.save(purchase);
				}
				if(!successfulOperation) {
					HibernateUtil.rollbackTransaction();
				} else {
					HibernateUtil.commitTransaction();
				}
			}	
		} catch (Exception ex){
			HibernateUtil.rollbackTransaction();
			LOG.error("Make purchase transaction failed", ex);
			successfulOperation = false;
		}
		return successfulOperation;
	}
	
	@Override
	public List<Purchase> getPurchaseByCustomer(Customer customer) {
		List<Purchase> purchases = null;
		try {
			HibernateUtil.beginTransaction();
			purchases = purchaseDao.getPurchaseByCustomer(customer);
			HibernateUtil.commitTransaction();
		} catch (Exception ex){
			HibernateUtil.rollbackTransaction();
			LOG.error("Get Purchase by Customer transaction failed", ex);
		}
		return purchases != null ? purchases : new ArrayList<Purchase>();
	}
}
