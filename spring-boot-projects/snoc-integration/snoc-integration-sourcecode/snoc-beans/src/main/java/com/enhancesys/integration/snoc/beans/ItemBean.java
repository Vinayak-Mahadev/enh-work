package com.enhancesys.integration.snoc.beans;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <b>Purpose:</b><br>
 *		Declaration of the Item bean properties..<br>
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
 * 1        15-07-2019          Vinayak Mahadev
 *     	-- Base Release
 * </pre>
 * 
 * <br>
 */
@XmlRootElement(name="itemBean")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "itemObj")
public class ItemBean implements Serializable
{
	private static final long serialVersionUID = -145966191202390049L;

	public String getMaterialCode() 
	{
		return materialCode;
	}

	public void setMaterialCode(String materialCode) 
	{
		this.materialCode = materialCode;
	}

	public String getQuantity() 
	{
		return quantity;
	}

	public void setQuantity(String quantity) 
	{
		this.quantity = quantity;
	}

	public String getUnitPrice() 
	{
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) 
	{
		this.unitPrice = unitPrice;
	}

	@XmlElement(name = "materialCode", required=true)
	private String materialCode;
	
	@XmlElement(name = "quantity", required=true)
	private String quantity;
	
	@XmlElement(name = "unitPrice")
	private String unitPrice;
}