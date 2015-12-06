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
@Table(name = "peyment", schema = "public")
public class Peyment implements java.io.Serializable {
	@Id
	@Column(name = "peyment_id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long peymentId;
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "description")
	private String description;
	@Column(name = "visible", nullable = false)
	private boolean visible;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "peyment")
	private List<Purchase> purchases = new ArrayList<Purchase>();

	public Peyment() {
	}

	public Peyment(Long peymentId, String name, boolean visible) {
		this.peymentId = peymentId;
		this.name = name;
		this.visible = visible;
	}

	public Peyment(Long peymentId, String name, String description,
			boolean visible, List<Purchase> purchases) {
		this.peymentId = peymentId;
		this.name = name;
		this.description = description;
		this.visible = visible;
		this.purchases = purchases;
	}

	public Long getPeymentId() {
		return this.peymentId;
	}

	public void setPeymentId(Long peymentId) {
		this.peymentId = peymentId;
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
