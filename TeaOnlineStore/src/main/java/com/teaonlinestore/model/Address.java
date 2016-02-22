package com.teaonlinestore.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address implements Serializable {
	private String city;
	private String street;
	
	public Address() {
	}
	
	public Address(String city, String street) {
		this.city = city;
		this.street = street;
	}
	
	@Column(name = "city")
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	@Column(name = "street")
	public String getStreet() {
		return street;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}
	
}
