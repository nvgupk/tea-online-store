package com.teaonlinestore.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.teaonlinestore.model.Product;
import com.teaonlinestore.utils.HibernateUtil;

public class ProductDaoHibernate extends GenericDaoHibernate<Product, Long> implements ProductDao {
	
	@Override
	public List<Product> getMostPopularProducts(int selectionsSize) {
		Session session = HibernateUtil.getSession();
		Query query1 = session.createQuery("select pp.id.product.productId from PurchaseProduct pp group by pp.id.product.productId order by sum(quantity)");
		query1.setMaxResults(selectionsSize);
		List<Long> productIds = query1.list();
		if(productIds == null || productIds.isEmpty()) {
			return new ArrayList<Product>();
		}
		Query query2 = session.createQuery("from Product p where p.productId in (:productIds)");
		query2.setParameterList("productIds", productIds);
		return query2.list();
	}
	
	@Override
	public List<Product> getProductsByPartialName(String partialName) {
		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(Product.class);
		criteria.add(Restrictions.ilike("name", partialName, MatchMode.ANYWHERE));
		return criteria.list();
	}
}
