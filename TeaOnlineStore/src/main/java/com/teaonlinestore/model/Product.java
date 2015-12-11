package com.teaonlinestore.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "product", schema = "public")
@Inheritance(strategy = InheritanceType.JOINED)
public class Product implements java.io.Serializable {
	@Id
	@Column(name = "product_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long productId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "quentity", nullable = false)
	private int quentity;
	
	@Column(name = "price", nullable = false)
	private double price;
	
	@Column(name = "description")
	private String description;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
	private List<PurchaseProduct> purhaseProducts = new ArrayList<PurchaseProduct>();

	public Product() {
	}

	public Product(Long productId, Category category, String name, int quentity, double price, String descriprion) {
		this.productId = productId;
		this.category = category;
		this.name = name;
		this.quentity = quentity;
		this.price = price;
		this.description = descriprion;
	}

	public Long getProductId() {
		return this.productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuentity() {
		return this.quentity;
	}

	public void setQuentity(int quentity) {
		this.quentity = quentity;
	}
	
	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public List<PurchaseProduct> getPurhaseProducts() {
		return this.purhaseProducts;
	}

	public void setPurhaseProducts(List<PurchaseProduct> purhaseProducts) {
		this.purhaseProducts = purhaseProducts;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
