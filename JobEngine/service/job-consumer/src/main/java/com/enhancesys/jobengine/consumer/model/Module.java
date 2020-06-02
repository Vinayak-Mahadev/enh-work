package com.enhancesys.jobengine.consumer.model;

import java.sql.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name = "com.enhancesys.jobengine.consumer.model.Module")
@Table(name="MS_MODULE",schema = "BIZ")
@Access(AccessType.FIELD)
public class Module {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MODULE_ID_N")
	protected Long _moduleId;

	@Column(name = "REF_ID_N")
	protected Long _refId;

	@Column(name = "NAME_V")
	protected String _name;

	@Column(name = "DESCRIPTION_V")
	protected String _desc;

	@Column(name = "CREATED_TIME_DT")
	protected Date _createdDate;

	@Column(name = "LAST_UPDATED_TIME_DT")
	protected Date _updatedDate;

	@Column(name = "CLOSED_TIME_DT")
	protected Date _closedDate;


	public Long get_moduleId() {
		return _moduleId;
	}

	public Long get_refId() {
		return _refId;
	}

	public String get_name() {
		return _name;
	}

	public String get_desc() {
		return _desc;
	}

	public Date get_createdDate() {
		return _createdDate;
	}

	public Date get_updatedDate() {
		return _updatedDate;
	}

	public Date get_closedDate() {
		return _closedDate;
	}

	public void set_moduleId(Long _moduleId) {
		this._moduleId = _moduleId;
	}

	public void set_refId(Long _refId) {
		this._refId = _refId;
	}

	public void set_name(String _name) {
		this._name = _name;
	}

	public void set_desc(String _desc) {
		this._desc = _desc;
	}

	public void set_createdDate(Date _createdDate) {
		this._createdDate = _createdDate;
	}

	public void set_updatedDate(Date _updatedDate) {
		this._updatedDate = _updatedDate;
	}

	public void set_closedDate(Date _closedDate) {
		this._closedDate = _closedDate;
	}


	@OneToMany(fetch=FetchType.LAZY, mappedBy="Module")
	protected java.util.Set<com.enhancesys.jobengine.consumer.model.ModuleAttributes> _moduleAttr;// = new java.util.HashSet<org.biz.product.entities.Shops>();


	public java.util.Set<com.enhancesys.jobengine.consumer.model.ModuleAttributes> get_moduleAttr() {
		return _moduleAttr;
	}

	public void set_moduleAttr(java.util.Set<com.enhancesys.jobengine.consumer.model.ModuleAttributes> _moduleAttr) {
		this._moduleAttr = _moduleAttr;
	}

	@OneToMany(fetch=FetchType.LAZY, mappedBy="Module")
	protected java.util.Set<com.enhancesys.jobengine.consumer.model.ModuleSummary> _moduleSummary;// = new java.util.HashSet<org.biz.product.entities.Shops>();


	public java.util.Set<com.enhancesys.jobengine.consumer.model.ModuleSummary> get_moduleSummary() {
		return _moduleSummary;
	}

	public void set__moduleSummary(java.util.Set<com.enhancesys.jobengine.consumer.model.ModuleSummary> _moduleSummary) {
		this._moduleSummary = _moduleSummary;
	}

	
	//	@JsonProperty(Constants.ORG_SHOP_LIST_for_JSON)
	//	//@JoinColumn(name="ORG_ID_N")
	//		public java.util.Set<org.biz.product.entities.Shops> get_shops() {
	//			return _shops;
	//		}
	//	
	//		public void set_shops(java.util.Set<org.biz.product.entities.Shops> _shops) {
	//			this._shops = _shops;
	//		} 

}
