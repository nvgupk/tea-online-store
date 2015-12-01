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
@Table(name = "attribute", schema = "public")
public class Attribute implements java.io.Serializable {
	@Id
	@Column(name = "attribute_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long attributeId;
	@Column(name = "name", nullable = false)
	private String name;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "attribute")
	private List<AtributeValueText> atributeValueTexts = new ArrayList<AtributeValueText>();
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "attribute")
	private List<AttributeValueDouble> attributeValueDoubles = new ArrayList<AttributeValueDouble>();

	public Attribute() {
	}

	public Attribute(long attributeId, String name) {
		this.attributeId = attributeId;
		this.name = name;
	}

	public Attribute(long attributeId, String name,
			List<AtributeValueText> atributeValueTexts,
			List<AttributeValueDouble> attributeValueDoubles) {
		this.attributeId = attributeId;
		this.name = name;
		this.atributeValueTexts = atributeValueTexts;
		this.attributeValueDoubles = attributeValueDoubles;
	}

	public long getAttributeId() {
		return this.attributeId;
	}

	public void setAttributeId(long attributeId) {
		this.attributeId = attributeId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<AtributeValueText> getAtributevaluetexts() {
		return this.atributeValueTexts;
	}

	public void setAtributevaluetexts(List<AtributeValueText> atributeValueTexts) {
		this.atributeValueTexts = atributeValueTexts;
	}

	public List<AttributeValueDouble> getAttributevaluedoubles() {
		return this.attributeValueDoubles;
	}

	public void setAttributevaluedoubles(List<AttributeValueDouble> attributeValueDoubles) {
		this.attributeValueDoubles = attributeValueDoubles;
	}

}
