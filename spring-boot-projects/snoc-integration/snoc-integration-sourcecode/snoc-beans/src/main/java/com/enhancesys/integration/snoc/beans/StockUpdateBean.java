package com.enhancesys.integration.snoc.beans;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * <b>Purpose:</b><br>
 * Declaration of the StockUpdate service parameter properties as StockUpdateBean class..<br>
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
 * 1       26-09-2014          Vinayak Mahadev
 * -- Base Release
 */
@XmlRootElement(name = "stockUpdateBean")
public class StockUpdateBean
{
	private String productId;
	private String circleId;
	private Long availableStock;
	
	public String getProductId() 
	{
		return productId;
	}
	
	public void setProductId(String productId) 
	{
		this.productId = productId;
	}
	
	public String getCircleId() 
	{
		return circleId;
	}
	
	public void setCircleId(String circleId) 
	{
		this.circleId = circleId;
	}
	
	public Long getAvailableStock() 
	{
		return availableStock;
	}
	
	public void setAvailableStock(Long availableStock) 
	{
		this.availableStock = availableStock;
	}
}
