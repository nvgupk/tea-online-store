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
@Table(name = "status", schema = "public")
public class Status implements java.io.Serializable {
	@Id
	@Column(name = "status_id", nullable = false)
	private Long statusId;
	@Column(name = "name", nullable = false)
	private String name;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "status")
	private List<Purchase> purchases = new ArrayList<Purchase>(0);

	public Status() {
	}

	public Status(Long statusId, String name) {
		this.statusId = statusId;
		this.name = name;
	}

	public Status(Long statusId, String name, List<Purchase> purchases) {
		this.statusId = statusId;
		this.name = name;
		this.purchases = purchases;
	}

	
	public Long getStatusId() {
		return this.statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
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
