package com.enhancesys.integration.snoc.beans;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <b>Purpose:</b><br>
 *		Declaration of the ProductDetails bean properties..<br>
 * <br>
 * 
 * <b>DesignReference:</b><br>
 * <br>
 * <br>
 * 
 * <b>CopyRights:</b><br>
 * Enhancesys Innovations 2014<br>
 * <br>
 * 
 * <b>RevisionHistory:</b>
 * 
 * <pre>
 * <b>
 * Sl No   Modified Date        Author</b>
 * ==============================================
 * 1        01-12-2014          Suresh Upparu
 *     	-- Base Release
 * 2        11-12-2014          Ramana Rao K
 *     	-- Added the item name(product short name) field
 * </pre>
 * 
 * <br>
 */
@XmlRootElement(name="productDetailsBean")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "productDtlsBean")
public class ProductDetailsBean implements Serializable
{
	private static final long serialVersionUID = -145966191202390049L;

	public String getItemId()
	{
		return itemId;
	}

	public void setItemId(String itemId)
	{
		this.itemId = itemId;
	}

	public String getItemLatestVersionId()
	{
		return itemLatestVersionId;
	}

	public void setItemLatestVersionId(String itemLatestVersionId)
	{
		this.itemLatestVersionId = itemLatestVersionId;
	}

	public String getItemLocationId()
	{
		return itemLocationId;
	}

	public void setItemLocationId(String itemLocationId)
	{
		this.itemLocationId = itemLocationId;
	}

	public String getItemLocationPrice()
	{
		return itemLocationPrice;
	}

	public void setItemLocationPrice(String itemLocationPrice)
	{
		this.itemLocationPrice = itemLocationPrice;
	}

	public String getItemName()
	{
		return itemName;
	}

	public void setItemName(String itemName)
	{
		this.itemName = itemName;
	}

	public String getItemCategoryId()
	{
		return itemCategoryId;
	}

	public void setItemCategoryId(String itemCategoryId)
	{
		this.itemCategoryId = itemCategoryId;
	}

	public String getSerialized()
	{
		return serialized;
	}

	public void setSerialized(String serialized)
	{
		this.serialized = serialized;
	}

	@XmlElement(name = "itemId")
	protected String itemId;
	
	@XmlElement(name = "itemLatestVersionId")
	protected String itemLatestVersionId;
	
	@XmlElement(name = "itemLocationId")
	protected String itemLocationId;
	
	@XmlElement(name = "itemLocationPrice")
	protected String itemLocationPrice	;	
	
	@XmlElement(name = "itemName")
	protected String itemName;
	
	@XmlElement(name = "itemCategoryId")
	protected String itemCategoryId;
	
	@XmlElement(name = "serialized")
	protected String serialized;
}
