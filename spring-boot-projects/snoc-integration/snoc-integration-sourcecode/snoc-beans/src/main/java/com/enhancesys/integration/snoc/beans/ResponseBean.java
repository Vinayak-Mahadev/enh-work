package com.enhancesys.integration.snoc.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;

/**
 * <b>Purpose:</b><br>
 *		Declaration of the Response bean properties..<br>
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
 * 1        21-07-2014          Vinayak Mahadev
 *     	-- Base Release
 * </pre>
 * 
 * <br>
 */
@XmlRootElement(name="responseBean")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "resBean")
public class ResponseBean implements Serializable
{
	private static final long serialVersionUID = -145966191202390049L;

	public String getAckData() 
	{
		return ackData;
	}

	public void setAckData(String ackData) 
	{
		this.ackData = ackData;
	}

	public Date getAckTime() 
	{
		return ackTime;
	}

	public void setAckTime(Date ackTime) 
	{
		this.ackTime = ackTime;
	}

	public String getResponseData() 
	{
		return responseData;
	}

	public void setResponseData(String responseData) 
	{
		this.responseData = responseData;
	}

	public Date getResponseTime() 
	{
		return responseTime;
	}

	public void setResponseTime(Date responseTime) 
	{
		this.responseTime = responseTime;
	}

	public String getStatus() 
	{
		return status;
	}

	public void setStatus(String status) 
	{
		this.status = status;
	}

	public Long getErrorCode() 
	{
		return errorCode;
	}

	public void setErrorCode(Long errorCode) 
	{
		this.errorCode = errorCode;
	}

	public String getErrorMessage() 
	{
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) 
	{
		this.errorMessage = errorMessage;
	}

	public Long getTransactionId() 
	{
		return transactionId;
	}

	public void setTransactionId(Long transactionId) 
	{
		this.transactionId = transactionId;
	}

	public Long getStatusId() 
	{
		return statusId;
	}

	public void setStatusId(Long statusId) 
	{
		this.statusId = statusId;
	}

	public Long getRetryCount() 
	{
		return retryCount;
	}

	public void setRetryCount(Long retryCount) 
	{
		this.retryCount = retryCount;
	}

	public Long getToBeUpdatedTransId() 
	{
		return toBeUpdatedTransId;
	}

	public void setFlag(boolean flag) 
	{
		this.flag = flag;
	}
	
	public boolean getFlag() 
	{
		return flag;
	}

	public void setToBeUpdatedTransId(Long toBeUpdatedTransId) 
	{
		this.toBeUpdatedTransId = toBeUpdatedTransId;
	}

	public BasicDBObject getDataObject() 
	{
		return dataObject;
	}

	public void setDataObject(BasicDBObject dataObject)
	{
		this.dataObject = dataObject;
	}

	public BasicDBList getDataList() 
	{
		return dataList;
	}

	public void setDataList(BasicDBList dataList) 
	{
		this.dataList = dataList;
	}

	public Map<String, BasicDBObject> getBaiscDBMap() 
	{
		return baiscDBMap;
	}

	public void setBaiscDBMap(Map<String, BasicDBObject> baiscDBMap)
	{
		this.baiscDBMap = baiscDBMap;
	}

	public List<BasicDBObject> getBaiscDBList() 
	{
		return baiscDBList;
	}

	public void setBaiscDBList(List<BasicDBObject> baiscDBList) 
	{
		this.baiscDBList = baiscDBList;
	}

	public Long getInterfaceId() 
	{
		return interfaceId;
	}

	public void setInterfaceId(Long interfaceId) 
	{
		this.interfaceId = interfaceId;
	}

	public String getRefData1() 
	{
		return refData1;
	}

	public void setRefData1(String refData1) 
	{
		this.refData1 = refData1;
	}

	public String getRefData5() 
	{
		return refData5;
	}

	public void setRefData5(String refData5) 
	{
		this.refData5 = refData5;
	}

	public String getRefData6() 
	{
		return refData6;
	}

	public void setRefData6(String refData6) 
	{
		this.refData6 = refData6;
	}
	
	public String getRefData7() 
	{
		return refData7;
	}

	public void setRefData7(String refData7) 
	{
		this.refData7 = refData7;
	}
	

	public String getRefData8() 
	{
		return refData8;
	}

	public void setRefData8(String refData8) 
	{
		this.refData8 = refData8;
	}
	
	@XmlElement(name = "refData1")
	protected String refData1;
	
	@XmlElement(name = "refData5")
	protected String refData5;
	
	@XmlElement(name = "refData6")
	protected String refData6;
	
	@XmlElement(name = "refData7")
	protected String refData7;
	
	@XmlElement(name = "refData8")
	protected String refData8;
	
	@XmlElement(name = "ackData")
	protected String ackData;
	
	@XmlElement(name = "ackTime")
	protected Date ackTime;
	
	@XmlElement(name = "responseData")
	protected String responseData;
	
	@XmlElement(name = "responseTime")
	protected Date responseTime;
	
	@XmlElement(name = "status")
	protected String status;
	
	@XmlElement(name = "errorCode")
	protected Long errorCode;
	
	@XmlElement(name = "errorMessage")
	protected String errorMessage;
	
//	@XmlElement(name = "orderItemId")
//	protected Long orderItemId;
	
	@XmlElement(name = "transactionId")
	protected Long transactionId;
	
	@XmlElement(name = "interfaceId")
	protected Long interfaceId;
	
	@XmlElement(name = "statusId")
	protected Long statusId;
	
	@XmlElement(name = "retryCount")
	protected Long retryCount;
	
	@XmlElement(name = "toBeUpdatedTransId")
	protected Long toBeUpdatedTransId;
	
	@XmlElement(name = "flag")
	protected boolean flag;
	
	@XmlElement(name = "dataObject")
	protected BasicDBObject dataObject;
	
	@XmlElement(name = "dataList")
	protected BasicDBList dataList;
	
	@XmlElement(name = "baiscDBMap")
	protected Map<String,BasicDBObject> baiscDBMap;
	
	@XmlElement(name = "baiscDBList")
	protected List<BasicDBObject> baiscDBList;
}