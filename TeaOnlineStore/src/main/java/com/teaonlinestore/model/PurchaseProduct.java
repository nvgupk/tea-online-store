package com.teaonlinestore.model;


import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "purchaseproduct", schema = "public")
@AssociationOverrides({
	@AssociationOverride(name = "id.purchase", 
		joinColumns = @JoinColumn(name = "purchase_id")),
	@AssociationOverride(name = "id.product", 
		joinColumns = @JoinColumn(name = "product_id")) })
public class PurchaseProduct implements java.io.Serializable {
	private PurchaseProductId id = new PurchaseProductId();
	private Double price;
	private Integer quantity;
	
	public PurchaseProduct() {
	}

	@EmbeddedId
	public PurchaseProductId getId() {
		return this.id;
	}

	public void setId(PurchaseProductId id) {
		this.id = id;
	}

	@Transient
	public Product getProduct() {
		return id.getProduct();
	}

	public void setProduct(Product product) {
		id.setProduct(product);
	}
	
	@Transient
	public Purchase getPurchase() {
		return id.getPurchase();
	}

	public void setPurchase(Purchase purchase) {
		id.setPurchase(purchase);
	}
	
	@Column(name = "price", nullable = false)
	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	@Column(name = "quantity", nullable = false)
	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}
