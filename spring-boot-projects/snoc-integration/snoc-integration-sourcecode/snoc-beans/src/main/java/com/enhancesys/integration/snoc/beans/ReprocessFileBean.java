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
 * Declaration of the ReprocessFileBean service parameter properties..<br>
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
 * 1       14-03-2019		   Vinayak Mahadev
 *   -- Base Release
 */
@XmlRootElement(name = "reprocessFileBean")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "reprocessFileBeanRes")
public class ReprocessFileBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	@XmlElement(name = "errorCode")
	private Long errorCode;
	@XmlElement(name = "files")
	private List<Long> files;
	
	public Long getErrorCode() 
	{
		return errorCode;
	}
	
	public void setErrorCode(Long errorCode) 
	{
		this.errorCode = errorCode;
	}
	
	public List<Long> getFiles() 
	{
		if(files == null)
			files = new ArrayList<Long>();
		return files;
	}
}