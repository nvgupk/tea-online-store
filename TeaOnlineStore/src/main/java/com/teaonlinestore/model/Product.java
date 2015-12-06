package com.teaonlinestore.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

@Entity
@Table(name = "product", schema = "public")
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
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
	private List<PurchaseProduct> purhaseProducts = new ArrayList<PurchaseProduct>();
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
	private List<AttributeValueText> attributeValueTexts = new ArrayList<AttributeValueText>();
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
	private List<AttributeValueDouble> attributeValueDoubles = new ArrayList<AttributeValueDouble>();

	public Product() {
	}

	public Product(Long productId, Category category, String name,
			int quentity, double price) {
		this.productId = productId;
		this.category = category;
		this.name = name;
		this.quentity = quentity;
		this.price = price;
	}

	public Product(Long productId, Category category, String name,
			int quentity, double price, List<PurchaseProduct> purhaseProducts,
			List<AttributeValueText> attributeValueTexts,
			List<AttributeValueDouble> attributeValueDoubles) {
		this.productId = productId;
		this.category = category;
		this.name = name;
		this.quentity = quentity;
		this.price = price;
		this.purhaseProducts = purhaseProducts;
		this.attributeValueTexts = attributeValueTexts;
		this.attributeValueDoubles = attributeValueDoubles;
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

	public List<AttributeValueText> getAtributeValueTexts() {
		return this.attributeValueTexts;
	}

	public void setAtributeValueTexts(List<AttributeValueText> attributeValueTexts) {
		this.attributeValueTexts = attributeValueTexts;
	}

	public List<AttributeValueDouble> getAttributeValueDoubles() {
		return this.attributeValueDoubles;
	}

	public void setAttributeValueDoubles(List<AttributeValueDouble> attributeValueDoubles) {
		this.attributeValueDoubles = attributeValueDoubles;
	}

}
