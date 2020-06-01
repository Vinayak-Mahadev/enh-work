package com.enhancesys.jobengine.consumer.model;

import java.sql.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.enhancesys.common.Constants;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity(name = "com.enhancesys.jobengine.consumer.model.Shop")
@Table(name="MS_SHOPS",schema = "BIZ")
@Access(AccessType.FIELD)
public class Shop {

	@Id
	@Column(name = "SHOP_ID_N")
	protected Long _shopId;

	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ORG_ID_N")
	protected Long _orgId;

	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "SHOP_TYPE_N")
	protected Long _shopType;

	@Column(name = "NAME_V")
	protected String _name;

	@Column(name = "VALUE_V")
	protected String _desc;

	@Column(name = "LAST_UPDATED_TIME_DT")
	protected Date _updatedDate;

	@Column(name = "CREATED_TIME_DT")
	protected Date _createdDate;

	@Column(name = "CLOSED_TIME_DT")
	protected Date _closedDate;



	@JsonProperty(Constants.SHOP_SHOP_ID_N_for_JSON)
	public Long get_shopId() {
		return _shopId;
	}

	@JsonProperty(Constants.SHOP_ORG_ID_N_for_JSON)
	public Long get_orgId() {
		return _orgId;
	}

	@JsonProperty(Constants.SHOP_SHOP_TYPE_N_for_JSON)
	public Long get_shopType() {
		return _shopType;
	}

	@JsonProperty(Constants.SHOP_NAME_V_for_JSON)
	public String get_name() {
		return _name;
	}

	@JsonProperty(Constants.SHOP_VALUE_V_for_JSON)
	public String get_desc() {
		return _desc;
	}

	@JsonProperty(Constants.SHOP_LAST_UPDATED_TIME_DT_for_JSON)
	public Date get_updatedDate() {
		return _updatedDate;
	}

	@JsonProperty(Constants.SHOP_CREATED_TIME_DT_for_JSON)
	public Date get_createdDate() {
		return _createdDate;
	}

	@JsonProperty(Constants.SHOP_CLOSED_TIME_DT_for_JSON)
	public Date get_closedDate() {
		return _closedDate;
	}

	public void set_shopId(Long _shopId) {
		this._shopId = _shopId;
	}

	public void set_orgId(Long _orgId) {
		this._orgId = _orgId;
	}

	public void set_shopType(Long _shopType) {
		this._shopType = _shopType;
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

	public void set_createdDate(Date _createdDate) {
		this._createdDate = _createdDate;
	}

	public void set_closedDate(Date _closedDate) {
		this._closedDate = _closedDate;
	}



	//	@ManyToOne//(fetch=FetchType.EAGER)
	//	@JoinColumn(name="ORG_ID_N", nullable=false)
	//	protected org.biz.product.entities.Organization _organization; 


	//	@OneToMany(fetch=FetchType.LAZY, mappedBy="organization")
	//	@JsonProperty(Constants.SHOP_ITEM_LIST_for_JSON)
	//	protected java.util.Set<org.biz.product.entities.Item> _items = new java.util.HashSet<org.biz.product.entities.Item>();




	//	public org.biz.product.entities.Organization get_organization() {
	//		return _organization;
	//	}
	//
	//
	//	public void set_organization(org.biz.product.entities.Organization _organization) {
	//		this._organization = _organization;
	//	}


	//	public java.util.Set<org.biz.product.entities.Item> get_items() {
	//		return _items;
	//	}
	//
	//
	//	public void set_items(java.util.Set<org.biz.product.entities.Item> _items) {
	//		this._items = _items;
	//	} 


}
