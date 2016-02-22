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
	private Long productId;
	private Category category;
	private String name;
	private Integer quentity;
	private Double price;
	private String maker;
	private String image;
	private String kind;
	private String description;
	private List<PurchaseProduct> purhaseProducts = new ArrayList<PurchaseProduct>();

	public Product() {
	}
	
	@Id
	@Column(name = "product_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getProductId() {
		return this.productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}
	
	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false)
	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	@Column(name = "maker", nullable = false)
	public String getMaker() {
		return maker;
	}

	public void setMaker(String maker) {
		this.maker = maker;
	}
	
	@Column(name = "name", nullable = false)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "img_path")
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	@Column(name = "quentity", nullable = false)
	public Integer getQuentity() {
		return this.quentity;
	}

	public void setQuentity(Integer quentity) {
		this.quentity = quentity;
	}
	
	@Column(name = "price", nullable = false)
	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "id.product")
	public List<PurchaseProduct> getPurhaseProducts() {
		return this.purhaseProducts;
	}

	public void setPurhaseProducts(List<PurchaseProduct> purhaseProducts) {
		this.purhaseProducts = purhaseProducts;
	}
	
	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name = "kind", nullable = false)
	public String getKind() {
		return this.kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}
	
	@Override
	public boolean equals(Object other) {
		if(other == null) {
			return false;
		}
		if(this.getClass() != other.getClass()) {
			return false;
		}
		if(this == other) {
			return true;
		}
		Product castOther = (Product) other;
		if(this.productId == castOther.productId) {
			return true;
		}
		return false;	
	}
	
	@Override
	public int hashCode() {
		Integer result = 17;
		result = (int) (37 * result + this.productId);
		return result;
	}
	
}
