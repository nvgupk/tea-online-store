package com.teaonlinestore.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "payment", schema = "public")
public class Payment implements java.io.Serializable {
	private Long paymentId;
	private String name;
	private String description;
	private Boolean visible;
	private List<Purchase> purchases = new ArrayList<Purchase>();
	
	public Payment() {
	}

	public Payment(String name, Boolean visible) {
		this.name = name;
		this.visible = visible;
	}
	
	@Id
	@Column(name = "payment_id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getPaymentId() {
		return this.paymentId;
	}

	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}
	
	@Column(name = "name", nullable = false)
	public String getName() {
		return this.name;
	}
	
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "description")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name = "visible", nullable = false)
	public Boolean getVisible() {
		return this.visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "payment")
	public List<Purchase> getPurhases() {
		return this.purchases;
	}

	public void setPurhases(List<Purchase> purchases) {
		this.purchases = purchases;
	}

}
