package com.enhancesys.jobengine.consumer.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.enhancesys.common.Constants;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity(name = "org.enh.finevm.ztest.entities.Item")
@Table(name="MS_ITEMS",schema = "BIZ")
//@JsonProperty(Constants.ITEM_ITEM_for_JSON)
public class Item {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ITEM_ID_N")
	protected Long _itemId;

	@Column(name = "SHOP_ID_N")
	protected Long _shopId;

	@Column(name = "NAME_V")
	protected String _name;

	@Column(name = "VALUE_V")
	protected String _desc;

	@Column(name = "LAST_UPDATED_TIME_DT")
	protected Date _updatedDate;

	
	@JsonProperty(Constants.ITEM_ITEM_ID_N_for_JSON)
	public Long get_itemId() {
		return _itemId;
	}

	@JsonProperty(Constants.ITEM_SHOP_ID_N_for_JSON)
	public Long get_shopId() {
		return _shopId;
	}

	@JsonProperty(Constants.ITEM_NAME_V_for_JSON)
	public String get_name() {
		return _name;
	}

	@JsonProperty(Constants.ITEM_VALUE_V_for_JSON)
	public String get_desc() {
		return _desc;
	}

	@JsonProperty(Constants.ITEM_LAST_UPDATED_TIME_DT_for_JSON)
	public Date get_updatedDate() {
		return _updatedDate;
	}

	public void set_itemId(Long _itemId) {
		this._itemId = _itemId;
	}

	public void set_shopId(Long _shopId) {
		this._shopId = _shopId;
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