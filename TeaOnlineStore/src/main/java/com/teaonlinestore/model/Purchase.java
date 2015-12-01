package com.teaonlinestore.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "purchase", schema = "public")
public class Purchase implements java.io.Serializable {
	@Id
	@Column(name = "purchase_id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long purchaseId;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id", nullable = false)
	private Customer customer;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "delivery_id", nullable = false)
	private Delivery delivery;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "peyment_id", nullable = false)
	private Peyment peyment;
	@Column(name = "purchase_date", nullable = false, length = 13)
	private Date purchaseDate;
	@Column(name = "additional_info")
	private String additionalInfo;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "purchase")
	private List<PurchaseProduct> purchaseProducts = new ArrayList<PurchaseProduct>();

	public Purchase() {
	}

	public Purchase(long purchaseId, Customer customer, Delivery delivery,
			Peyment peyment, Date purchaseDate) {
		this.purchaseId = purchaseId;
		this.customer = customer;
		this.delivery = delivery;
		this.peyment = peyment;
		this.purchaseDate = purchaseDate;
	}

	public Purchase(long purchaseId, Customer customer, Delivery delivery,
			Peyment peyment, Date purchaseDate, String additionalInfo,
			List<PurchaseProduct> purchaseproducts) {
		this.purchaseId = purchaseId;
		this.customer = customer;
		this.delivery = delivery;
		this.peyment = peyment;
		this.purchaseDate = purchaseDate;
		this.additionalInfo = additionalInfo;
		this.purchaseProducts = purchaseproducts;
	}

	public long getpurchaseId() {
		return this.purchaseId;
	}

	public void setpurchaseId(long purchaseId) {
		this.purchaseId = purchaseId;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Delivery getDelivery() {
		return this.delivery;
	}

	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
	}

	public Peyment getPeyment() {
		return this.peyment;
	}

	public void setPeyment(Peyment peyment) {
		this.peyment = peyment;
	}

	public Date getPurchaseDate() {
		return this.purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public String getAdditionalInfo() {
		return this.additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	public List<PurchaseProduct> getpurchaseproducts() {
		return this.purchaseProducts;
	}

	public void setpurchaseproducts(List<PurchaseProduct> purchaseProducts) {
		this.purchaseProducts = purchaseProducts;
	}

}

