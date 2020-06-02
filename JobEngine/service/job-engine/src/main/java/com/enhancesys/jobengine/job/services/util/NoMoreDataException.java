package com.enhancesys.jobengine.job.services.util;

/**
 * <b>Purpose:</b><br>
 * 		Class to implement the no more data exception properties..<br>
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
 *  1        19-11-2018          Suresh Upparu
 *    	-- Base Release
 * </pre>
 * 
 * <br>
 */
public class NoMoreDataException extends Exception
{
	private static final long serialVersionUID = 1L;
	private String errorCode;
	
	public NoMoreDataException()
	{
		super();
	}
	
	public NoMoreDataException(String message)
	{
		super(message);
	}

	public NoMoreDataException(String message, Throwable cause)
	{
		super(message, cause);
	}
	
	public NoMoreDataException(String errorCode, String message)
	{
		super(message);
		this.errorCode = errorCode;
	}
	
	public NoMoreDataException(String errorCode, String message, Throwable cause)
	{
		super(message, cause);
		this.errorCode = errorCode;
	}
	
	public String getErrorCode()
	{
		return errorCode;
	}
}
