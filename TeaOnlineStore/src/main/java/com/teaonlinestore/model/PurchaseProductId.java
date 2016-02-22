package com.teaonlinestore.model;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class PurchaseProductId implements java.io.Serializable {
	private Product product;
	private Purchase purchase;

	public PurchaseProductId() {
	}

	public PurchaseProductId(Product product, Purchase purchase) {
		this.product = product;
		this.purchase = purchase;
	}
	
	@ManyToOne
	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@ManyToOne
	public Purchase getPurchase() {
		return this.purchase;
	}

	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}

	public boolean equals(Object other) {
		if (this == other)
			return true;
		if (other == null)
			return false;
		if (!(other instanceof PurchaseProductId))
			return false;
		PurchaseProductId castOther = (PurchaseProductId) other;
		return (product.getProductId() == castOther.product.getProductId())
				&& (purchase.getPurchaseId() == castOther.purchase.getPurchaseId());
	}

	public int hashCode() {
		int result = 17;
		result = (int) (37 * result + this.product.getProductId());
		result = (int) (37 * result + this.purchase.getPurchaseId());
		return result;
	}
}
