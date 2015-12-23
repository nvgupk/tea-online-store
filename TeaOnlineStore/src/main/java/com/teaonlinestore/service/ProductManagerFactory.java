package com.teaonlinestore.service;

import com.teaonlinestore.model.Category;

public class ProductManagerFactory {
	public static ProductManager createProductManager(Category category) {
		switch(category.getName().toLowerCase()) {
			case "���":
				return new TeaManager();
			case "����":
				return new CoffeManager();
			default:
				return new ProductManager();
		}
	}
}
