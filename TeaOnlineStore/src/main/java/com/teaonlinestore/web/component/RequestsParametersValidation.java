package com.teaonlinestore.web.component;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletRequest;

public class RequestsParametersValidation {
	private Set<String> parameterToCheckForNull = new HashSet<String>();
	private Set<String> parameterToCheckForNumber = new HashSet<String>();
	
	public RequestsParametersValidation() {
		setDefaultParameterToCheck();
	}

	public Set<String> getParameterToCheckForNull() {
		return parameterToCheckForNull;
	}

	public void setParameterToCheckForNull(Set<String> parameterToCheckForNull) {
		if(parameterToCheckForNull != null) {
			this.parameterToCheckForNull = parameterToCheckForNull;
		}
	}

	public Set<String> getParameterToCheckForNumber() {
		return parameterToCheckForNumber;
	}

	public void setParameterToCheckForNumber(Set<String> parameterToCheckForNumber) {
		if(parameterToCheckForNumber != null){
			this.parameterToCheckForNumber = parameterToCheckForNumber;
		}
	}
	
	public Boolean checkForNumber(ServletRequest request) {
		for (String paramName : parameterToCheckForNumber) {
			String paramValue = request.getParameter(paramName);
			try {
				Long.valueOf(paramValue);
			} catch(Exception ex) {
				return false;
			}
		}
		return true;
	}
	
	public Boolean checkForNull(ServletRequest request) {
		for (String paramName : parameterToCheckForNull) {
			String parameter = request.getParameter(paramName);
			if(parameter == null || parameter.equals("")) {
				return false;
			}
		}
		return true;
	}
	
	public void setDefaultParameterToCheck(){
	};
}
