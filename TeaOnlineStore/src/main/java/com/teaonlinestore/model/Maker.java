package com.teaonlinestore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "maker", schema = "public")
public class Maker {
	private Long makerId;
	private String name;

	public Maker() {
	}
	
	@Id
	@Column(name = "maker_id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getMakerId() {
		return this.makerId;
	}

	public void setMakerId(Long makerId) {
		this.makerId = makerId;
	}
	
	@Column(name = "name", nullable = false)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
