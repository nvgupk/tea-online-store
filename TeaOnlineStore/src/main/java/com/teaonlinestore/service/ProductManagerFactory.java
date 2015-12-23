package com.teaonlinestore.service;

import com.teaonlinestore.model.Category;

public class ProductManagerFactory {
	public static ProductManager createProductManager(Category category) {
		switch(category.getName().toLowerCase()) {
			case "чай":
				return new TeaManager();
			case "кава":
				return new CoffeManager();
			default:
				return new ProductManager();
		}
	}
}
