package enh.team.interfaces.soapservice;

import java.util.Date;

public class ResponseBean 
{
	private String status;
	private String responseData;
	private Long errorCode;
	private String errorMessage;
	private Date responseTime;
	private Long timeRequired;
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getResponseData() {
		return responseData;
	}
	public void setResponseData(String responseData) {
		this.responseData = responseData;
	}
	public Long getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(Long errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public Date getResponseTime() {
		return responseTime;
	}
	public void setResponseTime(Date responseTime) {
		this.responseTime = responseTime;
	}
	
	public Long getTimeRequired() {
		return timeRequired;
	}
	public void setTimeRequired(Long timeRequired) {
		this.timeRequired = timeRequired;
	}
	
	@Override
	public String toString() {
		return "ResponseBean [ "
				+ "\n  status = " + status + "\n  responseData = " + responseData
				+ "\n  errorCode = " + errorCode
				+ "\n  errorMessage = " + errorMessage
				+ "\n  responseTime = " + responseTime 
				+ "\n  timeRequired = " + timeRequired 
				+" in min (  "+(timeRequired/6000f)+" min  )"
				+ "\n]";
	}
	
	
	
	
}
