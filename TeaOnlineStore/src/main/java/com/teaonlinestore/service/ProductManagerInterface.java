package com.teaonlinestore.service;

import java.util.Collection;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.teaonlinestore.model.Product;

public interface ProductManagerInterface {
	public Product getProductById(Long productId);
	/**
	 * Method return the list of best selling Products
	 * @param selectionsSize the max size of returned list
	 * @return the result list
	 */
	public List<Product> getMostPopularProducts(int selectionsSize);
	
	/**
	 * Method return the list of Products, which names contains partialNames as substring.
	 * If there are no matches, method returns an empty {@link java.util.List List}
	 * @param partialName partial Product name
	 * @return the result list
	 */
	public List<Product> getProductsByPartialName(String partialName);
	public JSONObject parseProductToJSONObject(Product product);
	public JSONArray parseProductsToJSONArray(Collection<Product> products);
}
