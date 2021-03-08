package com.enhancesys.integration.snoc.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <b>Purpose:</b><br>
 *		Declaration of the OrderDetails bean properties..<br>
 * <br>
 * 
 * <b>DesignReference:</b><br>
 * <br>
 * <br>
 * 
 * <b>CopyRights:</b><br>
 * Enhancesys Innovations 20195<br>
 * <br>
 * 
 * <b>RevisionHistory:</b>
 * 
 * <pre>
 * <b>
 * Sl No   Modified Date        Author</b>
 * ==============================================
 * 1        23-01-2019          Vinayak Mahadev
 *     	-- Base Release
</pre>
 * 
 * <br>
 */
@XmlRootElement(name="orderDetailsBean")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "orderDtlsBean")
public class OrderDetailsBean 
{
	public String getSalesOrderNumber() 
	{
		return salesOrderNumber;
	}
	
	public void setSalesOrderNumber(String salesOrderNumber) 
	{
		this.salesOrderNumber = salesOrderNumber;
	}
	
	public String getInvoiceNumber() 
	{
		return invoiceNumber;
	}
	
	public void setInvoiceNumber(String invoiceNumber) 
	{
		this.invoiceNumber = invoiceNumber;
	}
	
	public String getNetPrice() 
	{
		return netPrice;
	}
	
	public void setNetPrice(String netPrice) 
	{
		this.netPrice = netPrice;
	}

	@XmlElement(name = "salesOrderNumber")
	private String salesOrderNumber;
	
	@XmlElement(name = "invoiceNumber")
	private String invoiceNumber;
	
	@XmlElement(name = "netPrice")
	private String netPrice;
}