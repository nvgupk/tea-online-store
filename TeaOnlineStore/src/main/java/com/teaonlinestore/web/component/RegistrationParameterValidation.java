package com.teaonlinestore.web.component;

public class RegistrationParameterValidation extends RequestsParametersValidation{
	@Override
	public void setDefaultParameterToCheck() {
		getParameterToCheckForNumber().add("name");
		getParameterToCheckForNumber().add("surname");
		getParameterToCheckForNumber().add("phonenumber");
		getParameterToCheckForNumber().add("email");
		getParameterToCheckForNumber().add("password");
	}
}
