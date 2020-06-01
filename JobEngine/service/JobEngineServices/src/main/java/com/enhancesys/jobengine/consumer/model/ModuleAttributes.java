package com.enhancesys.jobengine.consumer.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "com.enhancesys.jobengine.consumer.model.ModuleAttributes")
@Table(name="MS_MODULE_ATTR",schema = "BIZ")
//@JsonProperty(Constants.ITEM_ITEM_for_JSON)
public class ModuleAttributes {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ATTRIBUTE_ID_N")
	protected Long _attributeId;

	@Column(name = "MODULE_ID_N")
	protected Long _moduleId;

	@Column(name = "NAME_V")
	protected String _name;

	@Column(name = "VALUE_V")
	protected String _desc;

	@Column(name = "LAST_UPDATED_TIME_DT")
	protected Date _updatedDate;

	
	public Long get_attributeId() {
		return _attributeId;
	}

	public Long get_moduleId() {
		return _moduleId;
	}

	public String get_name() {
		return _name;
	}

	public String get_desc() {
		return _desc;
	}

	public Date get_updatedDate() {
		return _updatedDate;
	}

	public void set_attributeId(Long _attributeId) {
		this._attributeId = _attributeId;
	}

	public void set_moduleId(Long _moduleId) {
		this._moduleId = _moduleId;
	}

	public void set_name(String _name) {
		this._name = _name;
	}

	public void set_desc(String _desc) {
		this._desc = _desc;
	}

	public void set_updatedDate(Date _updatedDate) {
		this._updatedDate = _updatedDate;
	}

	
	
//
//	@ManyToOne(fetch=FetchType.EAGER)
//	@JoinColumn(name="SHOP_ID_N", nullable=false)
//	protected org.biz.product.entities.Shops _shop;



//	public org.biz.product.entities.Shops get_shop() {
//		return _shop;
//	}
//
//
//	public void set_shop(org.biz.product.entities.Shops _shop) {
//		this._shop = _shop;
//	} 


	
	
}