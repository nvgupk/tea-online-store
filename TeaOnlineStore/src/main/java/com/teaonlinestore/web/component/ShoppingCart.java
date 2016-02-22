package com.teaonlinestore.web.component;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.teaonlinestore.model.Product;
import com.teaonlinestore.service.ProductManager;
import com.teaonlinestore.service.ProductManagerInterface;

public class ShoppingCart implements Serializable {
	private Map<Long, CartProduct> products;
	private Double totalPrice;
	private Integer size;

	public ShoppingCart() {
		products = new HashMap<Long, CartProduct>();
		totalPrice = .0;
		size = 0;
	}
	
	public class CartProduct implements Serializable{
		private Product product;
		private Integer quantity;
		private Double totalPrice = .0;
		
		public CartProduct(Product product, Integer quantity) {
			this.product = product;
			this.quantity = quantity;
		}
		
		public Product getProduct() {
			return product;
		}
		
		public void setProduct(Product product) {
			this.product = product;
		}
		
		public Integer getQuantity() {
			return quantity;
		}
		
		public void setQuantity(Integer quantity) {
			this.quantity = quantity;
		}

		public Double getTotalPrice() {
			totalPrice = product.getPrice()*quantity;
			return totalPrice;
		}
		
	}
	
	public void addNewProduct(Long productId) {
		ProductManagerInterface productManager = new ProductManager();
		Product product = productManager.getProductById(productId);
		products.put(productId, new CartProduct(product, 1));
		size = products.size();
	}
	
	public void addProductsQuantity(Long productId, Integer quantity) {
		CartProduct cartProduct = products.get(productId);
		cartProduct.setQuantity(quantity);
		products.put(productId, cartProduct);
		size = products.size();
	}
	
	public void removeProduct(Long productId) {
		products.remove(productId);
		size = products.size();
	}
	
	public Integer getSize() {
		return size;
	}
	
	public JSONArray getShoppingCartAsJSONArray() {
		JSONArray jsonArray = new JSONArray();
		for (CartProduct cartProduct : products.values()) {
			Product product = cartProduct.getProduct();
			JSONObject json = new JSONObject();
			json.put("productId", product.getProductId());
			json.put("name", product.getName());
			json.put("price", product.getPrice());
			json.put("image", product.getImage());
			jsonArray.add(json);
		}
		return jsonArray;
	}
	
	public Map<Long, CartProduct> getProducts() {
		return new HashMap<Long, CartProduct>(products);
	}

	public void setProducts(Map<Long, CartProduct> products) {
		this.products = products;
		size = products.size();
	}

	public Double getTotalPrice() {
		totalPrice = .0;
		for (CartProduct cartProduct : products.values()) {
			Product product = cartProduct.getProduct();
			Integer quantity = cartProduct.getQuantity();
			totalPrice += product.getPrice()*quantity;
		}
		return totalPrice;
	}
	
	public Integer getProductsQuentity(Long productId) {
		Integer quantity = 0;
		CartProduct cartProduct = products.get(productId);
		if(cartProduct != null){
			quantity = cartProduct.getQuantity();
		}
		return quantity;
	}
	
	public Product getProductById(Long productId) {
		Product product = null;
		CartProduct cartProduct = products.get(productId);
		if(cartProduct != null){
			product = cartProduct.getProduct();
		}
		return product;
	}
}
