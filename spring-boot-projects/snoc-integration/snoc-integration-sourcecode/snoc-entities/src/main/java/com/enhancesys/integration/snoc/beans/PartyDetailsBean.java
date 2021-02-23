package com.enhancesys.integration.snoc.beans;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <b>Purpose:</b><br>
 *		Declaration of the PartyDetails bean properties..<br>
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
 * </pre>
 * 
 * <br>
 */
@XmlRootElement(name="partyDetailsBean")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "partyDtlsBean")
public class PartyDetailsBean implements Serializable
{
	private static final long serialVersionUID = -145966191202390049L;

	public String getPartyId() 
	{
		return partyId;
	}

	public void setPartyId(String partyId) 
	{
		this.partyId = partyId;
	}

	public String getRoleTypeId() 
	{
		return roleTypeId;
	}

	public void setRoleTypeId(String roleTypeId) 
	{
		this.roleTypeId = roleTypeId;
	}

	public String getExtPartyId() 
	{
		return extPartyId;
	}

	public void setExtPartyId(String extPartyId) 
	{
		this.extPartyId = extPartyId;
	}

	public String getPartnerRole() 
	{
		return partnerRole;
	}

	public void setPartnerRole(String partnerRole) 
	{
		this.partnerRole = partnerRole;
	}

	public String getAccountId() 
	{
		return accountId;
	}

	public void setAccountId(String accountId) 
	{
		this.accountId = accountId;
	}

	public String getFinAccount() 
	{
		return finAccount;
	}

	public void setFinAccount(String finAccount) 
	{
		this.finAccount = finAccount;
	}

	public String getUserId()
	{
		return userId;
	}

	public void setUserId(String userId) 
	{
		this.userId = userId;
	}
	
	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}
	
	
	public String getLogInId()
	{
		return logInId;
	}

	public void setLogInId(String logInId)
	{
		this.logInId = logInId;
	}

	public String getSupplierPartyId()
	{
		return supplierPartyId;
	}

	public void setSupplierPartyId(String supplierPartyId)
	{
		this.supplierPartyId = supplierPartyId;
	}

	public String getSupplierRoleTypeId()
	{
		return supplierRoleTypeId;
	}

	public void setSupplierRoleTypeId(String supplierRoleTypeId)
	{
		this.supplierRoleTypeId = supplierRoleTypeId;
	}

	public String getSupplierAccountId()
	{
		return supplierAccountId;
	}

	public void setSupplierAccountId(String supplierAccountId)
	{
		this.supplierAccountId = supplierAccountId;
	}

	public String getParentPartyId()
	{
		return parentPartyId;
	}

	public void setParentPartyId(String parentPartyId)
	{
		this.parentPartyId = parentPartyId;
	}

	public String getParentPartyRoleTypeId()
	{
		return parentPartyRoleTypeId;
	}

	public void setParentPartyRoleTypeId(String parentPartyRoleTypeId)
	{
		this.parentPartyRoleTypeId = parentPartyRoleTypeId;
	}

	public String getExtPartyRoleValue()
	{
		return extPartyRoleValue;
	}

	public void setExtPartyRoleValue(String extPartyRoleValue)
	{
		this.extPartyRoleValue = extPartyRoleValue;
	}

	@XmlElement(name = "partyId")
	protected String partyId;
	
	@XmlElement(name = "roleTypeId")
	protected String roleTypeId;
	
	@XmlElement(name = "extPartyRoleValue")
	protected String extPartyRoleValue;
	
	@XmlElement(name = "extPartyId")
	protected String extPartyId;
	
	@XmlElement(name = "partnerRole")
	protected String partnerRole;
	
	@XmlElement(name = "accountId")
	protected String accountId;
	
	@XmlElement(name = "finAccount")
	protected String finAccount;
	
	@XmlElement(name = "userId")
	protected String userId;
	
	@XmlElement(name = "name")
	protected String name;
	
	@XmlElement(name = "logInId")
	protected String logInId;
	
	@XmlElement(name = "supplierPartyId")
	protected String supplierPartyId;
	
	@XmlElement(name = "supplierRoleTypeId")
	protected String supplierRoleTypeId;
	
	@XmlElement(name = "supplierAccountId")
	protected String supplierAccountId;
	
	@XmlElement(name = "parentPartyId")
	protected String parentPartyId;
	
	@XmlElement(name = "parentPartyRoleTypeId")
	protected String parentPartyRoleTypeId;
}