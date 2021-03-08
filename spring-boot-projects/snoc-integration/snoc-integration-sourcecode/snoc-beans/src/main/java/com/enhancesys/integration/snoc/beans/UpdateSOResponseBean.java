package com.enhancesys.integration.snoc.beans;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <b>Purpose:</b><br>
 *		Declaration of the Update SO bean properties..<br>
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
@XmlRootElement(name="updateSOResponseBean")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "updateSOResponse")
public class UpdateSOResponseBean implements Serializable
{
	private static final long serialVersionUID = -145966191202390049L;

	public String getSapSONumber()
	{
		return sapSONumber;
	}

	public void setSapSONumber(String sapSONumber) 
	{
		this.sapSONumber = sapSONumber;
	}

	public String getMobiiPONumber() 
	{
		return mobiiPONumber;
	}

	public void setMobiiPONumber(String mobiiPONumber) 
	{
		this.mobiiPONumber = mobiiPONumber;
	}
	
	public String getStatus() 
	{
		return status;
	}

	public void setStatus(String status) 
	{
		this.status = status;
	}

	public String getRejectionNote() 
	{
		return rejectionNote;
	}

	public void setRejectionNote(String rejectionNote) 
	{
		this.rejectionNote = rejectionNote;
	}

	@XmlElement(name = "sapSONumber", required = true)
	private String sapSONumber;
	
	@XmlElement(name = "mobiiPONumber")
	private String mobiiPONumber;

	@XmlElement(name = "status", required = true)
	private String status;
	
	@XmlElement(name = "rejectionNote")
	private String rejectionNote;
}