package com.teaonlinestore.web.component;

public class OrderParametersValidation extends RequestsParametersValidation {
	
	@Override
	public void setDefaultParameterToCheck() {
		getParameterToCheckForNull().add("email");
		getParameterToCheckForNull().add("name");
		getParameterToCheckForNull().add("surname");
		getParameterToCheckForNull().add("phonenumber");
		getParameterToCheckForNull().add("city");
		getParameterToCheckForNull().add("delivery");
		getParameterToCheckForNull().add("payment");
		
		getParameterToCheckForNumber().add("delivery");
		getParameterToCheckForNumber().add("payment");
	}

}
