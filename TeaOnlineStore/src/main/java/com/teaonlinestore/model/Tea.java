package com.teaonlinestore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "tea", schema = "public")
@PrimaryKeyJoinColumn(name="product_id")
public class Tea extends Product {
	
	@Column(name = "leaf_shape", nullable = false)
	private String leafShape;

	public Tea() {
	}

	public Tea(String leafShape) {
		this.leafShape = leafShape;
	}

	public String getLeafShape() {
		return this.leafShape;
	}

	public void setLeafShape(String leafShape) {
		this.leafShape = leafShape;
	}
	
}
