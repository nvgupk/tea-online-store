package com.teaonlinestore.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "customer", schema = "public")
public class Customer implements java.io.Serializable {
	private Long customerId;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String password;
	private Address address;
	private List<Purchase> purchases = new ArrayList<Purchase>();

	public Customer() {
	}

	@Id
	@Column(name = "customer_id", nullable = false)
	@SequenceGenerator(sequenceName = "customer_id_seq", name = "CustomerIdSeq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CustomerIdSeq")
	public Long getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	@Column(name = "first_name", nullable = false)
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@Column(name = "last_name", nullable = false)
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Column(name = "email", unique = true, nullable = false)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name = "phone_number", nullable = false)
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	@Column(name = "password")
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Embedded
	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	public List<Purchase> getPurhases() {
		return this.purchases;
	}

	public void setPurhases(List<Purchase> purchases) {
		this.purchases = purchases;
	}

}
