package com.enhancesys.integration.snoc.exception;

public class ApplicationException extends Exception 
{


	private static final long serialVersionUID = 1L;

	public ApplicationException()
	{
		super();
	}

	public ApplicationException(String message)
	{
		super(message);
	}

	public ApplicationException(Throwable cause)
	{
		super(cause);
	}

	public ApplicationException(String message, Throwable cause)
	{
		super(message, cause);
	}


	private String errorCode;

	public String getErrorCode()
	{
		return errorCode;
	}
}
