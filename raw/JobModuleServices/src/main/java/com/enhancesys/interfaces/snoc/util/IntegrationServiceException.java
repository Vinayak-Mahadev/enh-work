package com.enhancesys.interfaces.snoc.util;

import org.springframework.stereotype.Component;

import com.enhancesys.interfaces.snoc.common.Constants;

@Component
public class IntegrationServiceException extends Exception {
	
	
	/**
	 * SerialVersionUID is a unique identifier for each class, 
	 * JVM uses it to compare the versions of the class ensuring that 
	 * the same class was used during Serialization is loaded during Deserialization.
	 */
	private static final long serialVersionUID = 123456789L;
	
	private String message;
	private int code;
	
	public IntegrationServiceException(){}
	
	public IntegrationServiceException(String exceptionMessage){
		this.message=exceptionMessage;
		this.code=Constants.EX_CODE_DEFAULT_ERROR;
	}
	
	public IntegrationServiceException(String exceptionMessage, int exceptionCode){
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
		return "IntegrationServiceException [message=" + message + ", code="
				+ code + "]";
	}
	

}
