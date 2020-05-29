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

@Entity(name = "org.enh.finevm.ztest.entities.Organization")
@Table(name="MS_ORGANIZATION",schema = "BIZ")
@Access(AccessType.FIELD)
public class Organization {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ORG_ID_N")
	protected Long _orgId;

	@Column(name = "ORG_REF_ID_N")
	protected Long _shopId;

	@Column(name = "NAME_V")
	protected String _name;

	@Column(name = "DESCRIPTION_V")
	protected String _desc;

	@Column(name = "LAST_UPDATED_TIME_DT")
	protected Date _updatedDate;

	@Column(name = "CREATED_TIME_DT")
	protected Date _createdDate;

	@Column(name = "CLOSED_TIME_DT")
	protected Date _closedDate;

	//	@OneToMany(fetch=FetchType.LAZY, mappedBy="organization")
	//	@JsonProperty(Constants.ORG_SHOP_LIST_for_JSON)
	//	@OneToMany(mappedBy = "MS_ORGANIZATION")
	//	//@JoinColumn(name="ORG_ID_N")
	//	protected java.util.Set<org.biz.product.entities.Shops> _shops;// = new java.util.HashSet<org.biz.product.entities.Shops>();


	public Organization() {

	}



	@JsonProperty(Constants.ORG_ORG_ID_N_for_JSON)
	public Long get_orgId() {
		return _orgId;
	}

	@JsonProperty(Constants.ORG_ORG_REF_ID_N_for_JSON)
	public Long get_shopId() {
		return _shopId;
	}

	@JsonProperty(Constants.ORG_NAME_V_for_JSON)
	public String get_name() {
		return _name;
	}

	@JsonProperty(Constants.ORG_DESCRIPTION_V_for_JSON)
	public String get_desc() {
		return _desc;
	}

	@JsonProperty(Constants.ORG_LAST_UPDATED_TIME_DT_for_JSON)
	public Date get_updatedDate() {
		return _updatedDate;
	}

	@JsonProperty(Constants.ORG_CREATED_TIME_DT_for_JSON)
	public Date get_createdDate() {
		return _createdDate;
	}

	@JsonProperty(Constants.ORG_CLOSED_TIME_DT_for_JSON)
	public Date get_closedDate() {
		return _closedDate;
	}


	public void set_orgId(Long _orgId) {
		this._orgId = _orgId;
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

	public void set_createdDate(Date _createdDate) {
		this._createdDate = _createdDate;
	}

	public void set_closedDate(Date _closedDate) {
		this._closedDate = _closedDate;
	}

	@Override
	public String toString() {
		return "Organization [_orgId=" + _orgId + ", _shopId=" + _shopId + ", _name=" + _name + ", _desc=" + _desc
				+ ", _updatedDate=" + _updatedDate + ", _createdDate=" + _createdDate + ", _closedDate=" + _closedDate
				+ "]";
	}


	//		public java.util.Set<org.biz.product.entities.Shops> get_shops() {
	//			return _shops;
	//		}
	//	
	//		public void set_shops(java.util.Set<org.biz.product.entities.Shops> _shops) {
	//			this._shops = _shops;
	//		} 

}
