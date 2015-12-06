package com.teaonlinestore.model;


import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "purchaseproduct", schema = "public")
public class PurchaseProduct implements java.io.Serializable {
	@EmbeddedId
	private PurchaseProductId id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id", nullable = false, insertable = false, updatable = false)
	private Product product;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "purchase_id", nullable = false, insertable = false, updatable = false)
	private Purchase purchase;
	@Column(name = "price", nullable = false)
	private double price;
	@Column(name = "quantity", nullable = false)
	private Long quantity;

	public PurchaseProduct() {
	}

	public PurchaseProduct(PurchaseProductId id, Product product,
			Purchase purchase, double price, Long quantity) {
		this.id = id;
		this.product = product;
		this.purchase = purchase;
		this.price = price;
		this.quantity = quantity;
	}

	
	public PurchaseProductId getId() {
		return this.id;
	}

	public void setId(PurchaseProductId id) {
		this.id = id;
	}

	
	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Purchase getPurchase() {
		return this.purchase;
	}

	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Long getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

}
