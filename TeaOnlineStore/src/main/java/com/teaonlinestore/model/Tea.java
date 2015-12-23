package com.teaonlinestore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "tea", schema = "public")
@PrimaryKeyJoinColumn(name="product_id")
public class Tea extends Product {
	
	@ManyToOne
	@JoinColumn(name = "maker_id", nullable = false)
	private Maker maker;
	
	@Column(name = "leaf_shape", nullable = false)
	private String leafShape;

	public Tea() {
	}

	public Tea(Maker maker, String leafShape) {
		this.maker = maker;
		this.leafShape = leafShape;
	}

	public Maker getMaker() {
		return this.maker;
	}

	public void setMaker(Maker maker) {
		this.maker = maker;
	}

	public String getLeafShape() {
		return this.leafShape;
	}

	public void setLeafShape(String leafShape) {
		this.leafShape = leafShape;
	}
	
}
