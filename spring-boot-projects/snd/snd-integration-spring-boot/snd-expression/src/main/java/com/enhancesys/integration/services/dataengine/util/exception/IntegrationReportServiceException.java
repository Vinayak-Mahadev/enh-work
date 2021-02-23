package com.enhancesys.integration.services.dataengine.util.exception;

import org.springframework.stereotype.Component;

import com.enhancesys.integration.services.dataengine.util.DataConstants;

@Component
public class IntegrationReportServiceException extends Exception {
	
	
	/**
	 * SerialVersionUID is a unique identifier for each class, 
	 * JVM uses it to compare the versions of the class ensuring that 
	 * the same class was used during Serialization is loaded during Deserialization.
	 */
	private static final long serialVersionUID = 123456789L;
	
	private String message;
	private int code;
	
	public IntegrationReportServiceException(){}
	
	public IntegrationReportServiceException(String exceptionMessage){
		this.message=exceptionMessage;
		this.code=DataConstants.EX_CODE_DEFAULT_ERROR;
	}
	
	public IntegrationReportServiceException(String exceptionMessage, int exceptionCode){
		this.message=exceptionMessage;
		this.code=exceptionCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "IntegrationReportServiceException [message=" + message + ", code="
				+ code + "]";
	}
	

}
