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
	
	@Column(name = "sort", nullable = false)
	private String sort;
	
	public Coffe() {
	}

	public Coffe(String sort) {
		this.sort = sort;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
	
}
