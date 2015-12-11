package com.teaonlinestore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.teaonlinestore.model.Product;

@Entity
@Table(name = "coffe", schema = "public")
@PrimaryKeyJoinColumn(name="product_id")
public class Coffe extends Product {
	
	@ManyToOne
	@JoinColumn(name = "maker_id", nullable = false)
	private Maker maker;
	
	@Column(name = "kind", nullable = false)
	private String kind;
	
	@Column(name = "sort", nullable = false)
	private String sort;
	
	public Coffe() {
	}

	public Coffe(Maker maker, String sort, String kind) {
		this.maker = maker;
		this.sort = sort;
		this.kind = kind;
	}

	public Maker getMaker() {
		return this.maker;
	}

	public void setMaker(Maker maker) {
		this.maker = maker;
	}
	
	public String getKind() {
		return this.kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
	
}
