package com.teaonlinestore.web.component;

public class PersonalParameterValidation extends RequestsParametersValidation{
	public void setDefaultParameterToCheck() {
		getParameterToCheckForNull().add("email");
		getParameterToCheckForNull().add("name");
		getParameterToCheckForNull().add("surname");
		getParameterToCheckForNull().add("phonenumber");
	}
}
