package com.enhancesys.integration.snoc.exception;

/**
 * <b>Purpose:</b><br>
 * 		Class to implement the generic process exception properties..<br>
 * <br>
 * <br>
 * <br>
 * 
 * <b>DesignReference:</b><br>
 * <br>
 * <br>
 * 
 * <b>CopyRights:</b><br>
 * 		Enhancesys Innovations 2018<br>
 * <br>
 * 
 * <b>RevisionHistory:</b>
 * 
 * <pre>
 * <b>
 *  Sl No   Modified Date        Author</b>
 *  ==============================================
 *  1        10-10-2018          Suresh Upparu
 *    	-- Base Release
 * </pre>
 * 
 * <br>
 */
public class GenericProcessorException extends Exception
{
	private static final long serialVersionUID = 1L;
	private String errorCode;
	
	public GenericProcessorException()
	{
		super();
	}
	
	public GenericProcessorException(String message)
	{
		super(message);
	}

	public GenericProcessorException(String message, Throwable cause)
	{
		super(message, cause);
	}
	
	public GenericProcessorException(String errorCode, String message)
	{
		super(message);
		this.errorCode = errorCode;
	}
	
	public GenericProcessorException(String errorCode, String message, Throwable cause)
	{
		super(message, cause);
		this.errorCode = errorCode;
	}
	
	public String getErrorCode()
	{
		return errorCode;
	}
}
