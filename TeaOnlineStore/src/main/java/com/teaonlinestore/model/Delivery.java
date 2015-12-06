package com.teaonlinestore.model;

import java.math.BigDecimal;
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
	@Id
	@Column(name = "delivery_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long deliveryId;
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "description")
	private String description;
	@Column(name = "price")
	private double price;
	@Column(name = "tariff")
	private String tariff;
	@Column(name = "visible", nullable = false)
	private boolean visible;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "delivery")
	private List<Purchase> purchases = new ArrayList<Purchase>();

	public Delivery() {
	}

	public Delivery(Long deliveryId, String name, boolean visible) {
		this.deliveryId = deliveryId;
		this.name = name;
		this.visible = visible;
	}

	public Delivery(Long deliveryId, String name, String description,
			double price, String tariff, boolean visible,
			List<Purchase> purchases) {
		this.deliveryId = deliveryId;
		this.name = name;
		this.description = description;
		this.price = price;
		this.tariff = tariff;
		this.visible = visible;
		this.purchases = purchases;
	}

	public Long getDeliveryId() {
		return this.deliveryId;
	}

	public void setDeliveryId(Long deliveryId) {
		this.deliveryId = deliveryId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getTariff() {
		return this.tariff;
	}

	public void setTariff(String tariff) {
		this.tariff = tariff;
	}

	public boolean isVisible() {
		return this.visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public List<Purchase> getPurhases() {
		return this.purchases;
	}

	public void setPurhases(List<Purchase> purchases) {
		this.purchases = purchases;
	}

}

