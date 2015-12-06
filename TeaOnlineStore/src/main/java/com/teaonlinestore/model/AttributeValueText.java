package com.teaonlinestore.model;
// default package
// Generated 11 лист. 2015 17:29:02 by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "atributevaluetext", schema = "public")
public class AttributeValueText implements java.io.Serializable {
	@Id
	@Column(name = "attribute_value_text_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long attributeValueTextId;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "attribute_id", nullable = false)
	private Attribute attribute;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	private Product product;
	@Column(name = "value", nullable = false)
	private String value;

	public AttributeValueText() {
	}

	public AttributeValueText(Long attributeValueTextId, Attribute attribute,
			String value) {
		this.attributeValueTextId = attributeValueTextId;
		this.attribute = attribute;
		this.value = value;
	}

	public AttributeValueText(Long attributeValueTextId, Attribute attribute,
			Product product, String value) {
		this.attributeValueTextId = attributeValueTextId;
		this.attribute = attribute;
		this.product = product;
		this.value = value;
	}

	public Long getAttributeValueTextId() {
		return this.attributeValueTextId;
	}

	public void setAttributeValueTextId(Long attributeValueTextId) {
		this.attributeValueTextId = attributeValueTextId;
	}

	public Attribute getAttribute() {
		return this.attribute;
	}

	public void setAttribute(Attribute attribute) {
		this.attribute = attribute;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
