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
@Table(name = "maker", schema = "public")
public class Maker {
	
	@Id
	@Column(name = "maker_id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long makerId;
	
	@Column(name = "name", nullable = false)
	private String name;

	public Maker() {
	}

	public Maker(Long makerId, String name) {
		this.makerId = makerId;
		this.name = name;
	}

	public Long getMakerId() {
		return this.makerId;
	}

	public void setMakerId(Long makerId) {
		this.makerId = makerId;
	}

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
