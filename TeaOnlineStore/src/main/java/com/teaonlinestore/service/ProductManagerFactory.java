package com.teaonlinestore.service;

import com.teaonlinestore.model.Category;

public class ProductManagerFactory {
	public static ProductManagerInterface createProductManager(Category category) {
		if(category == null) {
			return null;
		}
		switch(category.getName().toLowerCase()) {
			case "чай":
				return new TeaManager();
			case "кава":
				return new CoffeManager();
			default:
				return null;
		}
	}
}
