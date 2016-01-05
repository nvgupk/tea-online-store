package com.teaonlinestore.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
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
	@Id
	@Column(name = "customer_id", nullable = false)
	@SequenceGenerator(sequenceName = "customer_id_seq", name = "CustomerIdSeq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CustomerIdSeq")
	private Long customerId;
	
	@Column(name = "first_name", nullable = false)
	private String firstName;
	
	@Column(name = "last_name", nullable = false)
	private String lastName;
	
	@Column(name = "email", unique = true, nullable = false)
	private String email;
	
	@Column(name = "phone_number", nullable = false)
	private String phoneNumber;
	
	@Column(name = "password")
	private String password;
	
	@Embedded
	private Address address;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	private List<Purchase> purchases = new ArrayList<Purchase>();

	public Customer() {
	}

	public Customer(Long customerId, String firstName, String lastName, String email, String phoneNumber, String address) {
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}

	public Customer(Long customerId, String firstName, String lastName, String email, String phoneNumber, String password, Address address, List<Purchase> purchases) {
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.address = address;
		this.purchases = purchases;
	}

	
	public Long getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Purchase> getPurhases() {
		return this.purchases;
	}

	public void setPurhases(List<Purchase> purchases) {
		this.purchases = purchases;
	}

}
