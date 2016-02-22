package com.teaonlinestore.dao;


import java.util.List;

import com.teaonlinestore.model.Product;

public interface ProductDao extends GenericDao<Product, Long>{
	public List<Product> getMostPopularProducts(int selectionsSize);
	public List<Product> getProductsByPartialName(String partialName);
}
