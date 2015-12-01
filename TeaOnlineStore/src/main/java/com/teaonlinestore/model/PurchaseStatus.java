package com.teaonlinestore.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "purchase_status", schema = "public")
public class PurchaseStatus implements java.io.Serializable {
	@Id
	@Column(name = "purchase_status_id", nullable = false)
	private long purchaseStatusId;
	@Column(name = "name", nullable = false)
	private String name;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "purchaseStatus")
	private List<Purchase> purchases = new ArrayList<Purchase>(0);

	public PurchaseStatus() {
	}

	public PurchaseStatus(long purchaseStatusId, String name) {
		this.purchaseStatusId = purchaseStatusId;
		this.name = name;
	}

	public PurchaseStatus(long purchaseStatusId, String name, List<Purchase> purchases) {
		this.purchaseStatusId = purchaseStatusId;
		this.name = name;
		this.purchases = purchases;
	}

	
	public long getPurchaseStatusId() {
		return this.purchaseStatusId;
	}

	public void setPurchaseStatusId(long purchaseStatusId) {
		this.purchaseStatusId = purchaseStatusId;
	}

	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public List<Purchase> getPurchases() {
		return this.purchases;
	}

	public void setPurchases(List<Purchase> purchases) {
		this.purchases = purchases;
	}

}
