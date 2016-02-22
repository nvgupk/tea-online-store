package com.teaonlinestore.web.component;

public class ProductInfoParameterValidation extends RequestsParametersValidation{
	
	@Override
	public void setDefaultParameterToCheck() {
		getParameterToCheckForNumber().add("category_id");
	}
	
}
