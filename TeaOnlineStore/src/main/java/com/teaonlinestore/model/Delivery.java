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
@Table(name = "delivery", schema = "public")
public class Delivery implements java.io.Serializable {
	private Long deliveryId;
	private String name;
	private String description;
	private Double price;
	private String tariff;
	private Boolean visible;
	private List<Purchase> purchases = new ArrayList<Purchase>();

	public Delivery() {
	}
	
	@Id
	@Column(name = "delivery_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getDeliveryId() {
		return this.deliveryId;
	}

	public void setDeliveryId(Long deliveryId) {
		this.deliveryId = deliveryId;
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
	
	@Column(name = "price")
	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	@Column(name = "tariff")
	public String getTariff() {
		return this.tariff;
	}

	public void setTariff(String tariff) {
		this.tariff = tariff;
	}
	
	@Column(name = "visible", nullable = false)
	public Boolean getVisible() {
		return this.visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "delivery")
	public List<Purchase> getPurhases() {
		return this.purchases;
	}

	public void setPurhases(List<Purchase> purchases) {
		this.purchases = purchases;
	}
}

