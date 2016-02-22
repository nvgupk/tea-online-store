package com.teaonlinestore.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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
import javax.persistence.Transient;

@Entity
@Table(name = "purchase", schema = "public")
public class Purchase implements java.io.Serializable {
	private Long purchaseId;
	private Customer customer;
	private Delivery delivery;
	private Payment payment;
	private Status status;
	private Date purchaseDate;
	private String additionalInfo;
	private Double totalPrice;
	private Integer totalQuantity;
	private List<PurchaseProduct> purchaseProducts = new ArrayList<PurchaseProduct>();

	public Purchase() {
	}
	
	@Id
	@Column(name = "purchase_id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getPurchaseId() {
		return this.purchaseId;
	}

	public void setPurchaseId(Long purchaseId) {
		this.purchaseId = purchaseId;
	}
	
	@ManyToOne
	@JoinColumn(name = "customer_id", nullable = false)
	public Customer getCustomer() {
		return this.customer;
	}

	public Purchase setCustomer(Customer customer) {
		this.customer = customer;
		return this;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "delivery_id", nullable = false)
	public Delivery getDelivery() {
		return this.delivery;
	}

	public Purchase setDelivery(Delivery delivery) {
		this.delivery = delivery;
		return this;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "payment_id", nullable = false)
	public Payment getPayment() {
		return this.payment;
	}

	public Purchase setPayment(Payment payment) {
		this.payment = payment;
		return this;
	}
	
	@Column(name = "purchase_date", nullable = false, length = 13)
	public Date getPurchaseDate() {
		return this.purchaseDate;
	}

	public Purchase setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
		return this;
	}
	
	@Column(name = "additional_info")
	public String getAdditionalInfo() {
		return this.additionalInfo;
	}

	public Purchase setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
		return this;
	}
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "id.purchase", cascade=CascadeType.ALL)
	public List<PurchaseProduct> getPurchaseProducts() {
		return this.purchaseProducts;
	}

	public void setPurchaseProducts(List<PurchaseProduct> purchaseProducts) {
		this.purchaseProducts = purchaseProducts;
	}
	
	@ManyToOne
	@JoinColumn(name = "status_id")
	public Status getStatus() {
		return this.status;
	}

	public Purchase setStatus(Status status) {
		this.status = status;
		return this;
	}
	
	@Transient
	public Double getTotalPrice() {
		totalPrice = .0;
		for(int i = 0; i < purchaseProducts.size(); i++) {
			totalPrice += purchaseProducts.get(i).getPrice()*purchaseProducts.get(i).getQuantity();
		}
		return totalPrice;
	}
	
	@Transient
	public Integer getTotalQuantity() {
		totalQuantity = 0;
		for(int i = 0; i < purchaseProducts.size(); i++) {
			totalQuantity += purchaseProducts.get(i).getQuantity();
		}
		return totalQuantity;
	}

}

