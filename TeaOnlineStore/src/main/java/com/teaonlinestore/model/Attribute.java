package com.teaonlinestore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="attribute", schema = "public")
public class Attribute {
	private String attrName;
	private String displayName;
	
	public Attribute(){
		
	}
	
	public Attribute(String attrName, String displayName) {
		this.attrName = attrName;
		this.displayName = displayName;
	}
	
	@Id
	@Column(name = "attr_name", unique = true, nullable = false)
	public String getAttrName() {
		return attrName;
	}

	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}

	@Column(name = "display_name", nullable = false)
	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((attrName == null) ? 0 : attrName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(this.getClass() != obj.getClass())
			return false;
		Attribute other = (Attribute) obj;
		if (attrName == null && other.attrName != null) {
				return false;
		} else if (!attrName.equals(other.attrName))
			return false;
		return true;
	}
	
	
}
