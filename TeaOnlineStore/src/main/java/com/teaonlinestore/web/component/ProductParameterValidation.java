package com.teaonlinestore.web.component;

public class ProductParameterValidation extends RegistrationParameterValidation{
	
	@Override
	public void setDefaultParameterToCheck() {
		getParameterToCheckForNumber().add("category_id");
	}
	
}	
