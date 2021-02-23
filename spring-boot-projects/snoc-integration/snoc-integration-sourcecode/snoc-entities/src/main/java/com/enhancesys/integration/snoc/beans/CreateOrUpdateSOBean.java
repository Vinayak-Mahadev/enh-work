package com.enhancesys.integration.snoc.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <b>Purpose:</b><br>
 *		Declaration of the Create or Update SO bean properties..<br>
 * <br>
 * 
 * <b>DesignReference:</b><br>
 * <br>
 * <br>
 * 
 * <b>CopyRights:</b><br>
 * Enhancesys Innovations 2019<br>
 * <br>
 * 
 * <b>RevisionHistory:</b>
 * 
 * <pre>
 * <b>
 * Sl No   Modified Date        Author</b>
 * ==============================================
 * 1        15-07-2019          Suresh Upparu
 *     	-- Base Release
 * </pre>
 * 
 * <br>
 */
@XmlRootElement(name="createOrUpdateSOBean")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createOrUpdateSO")
public class CreateOrUpdateSOBean implements Serializable
{
	private static final long serialVersionUID = -145966191202390049L;

	public String getSoNumber() 
	{
		return soNumber;
	}

	public void setSoNumber(String soNumber) 
	{
		this.soNumber = soNumber;
	}

	public String getSourceChannel() 
	{
		return sourceChannel;
	}

	public void setSourceChannel(String sourceChannel) 
	{
		this.sourceChannel = sourceChannel;
	}

	public String getReferenceNumber() 
	{
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) 
	{
		this.referenceNumber = referenceNumber;
	}

	public String getSoStatus() 
	{
		return soStatus;
	}

	public void setSoStatus(String soStatus) 
	{
		this.soStatus = soStatus;
	}

	public String getSoAmount() 
	{
		return soAmount;
	}

	public void setSoAmount(String soAmount) 
	{
		this.soAmount = soAmount;
	}

	public String getOrderType() 
	{
		return orderType;
	}

	public void setOrderType(String orderType) 
	{
		this.orderType = orderType;
	}

	public String getDistributionChannel() 
	{
		return distributionChannel;
	}

	public void setDistributionChannel(String distributionChannel) 
	{
		this.distributionChannel = distributionChannel;
	}

	public String getDivision() 
	{
		return division;
	}

	public void setDivision(String division) 
	{
		this.division = division;
	}

	public String getSoldToParty() 
	{
		return soldToParty;
	}

	public void setSoldToParty(String soldToParty) 
	{
		this.soldToParty = soldToParty;
	}

	public String getShipToParty() 
	{
		return shipToParty;
	}

	public void setShipToParty(String shipToParty) 
	{
		this.shipToParty = shipToParty;
	}

	public String getPoDate() 
	{
		return poDate;
	}

	public void setPoDate(String poDate) 
	{
		this.poDate = poDate;
	}

	public String getRequestDeliveryDate() 
	{
		return requestDeliveryDate;
	}

	public void setRequestDeliveryDate(String requestDeliveryDate) 
	{
		this.requestDeliveryDate = requestDeliveryDate;
	}

	public List<ItemBean> getItems()
	{
		return items;
	}

	public void setItems(List<ItemBean> items) 
	{
		this.items = items;
	}

	@XmlElement(name = "soNumber", required=true)
	private String soNumber;
	
	@XmlElement(name = "sourceChannel", required=true)
	private String sourceChannel;
	
	@XmlElement(name = "referenceNumber", required=true)
	private String referenceNumber;
	
	@XmlElement(name = "soStatus", required=true)
	private String soStatus;
	
	@XmlElement(name = "soAmount", required=true)
	private String soAmount;
	
	@XmlElement(name = "orderType", required=true)
	private String orderType;
	
	@XmlElement(name = "distributionChannel", required=true)
	private String distributionChannel;
	
	@XmlElement(name = "division", required=true)
	private String division;
	
	@XmlElement(name = "soldToParty")
	private String soldToParty;
	
	@XmlElement(name = "shipToParty", required=true)
	private String shipToParty;
	
	@XmlElement(name = "poDate", required=true)
	private String poDate;
	
	@XmlElement(name = "requestDeliveryDate", required=true)
	private String requestDeliveryDate;
	
	@XmlElement(name = "item")
	private List<ItemBean> items = new ArrayList<ItemBean>();
}