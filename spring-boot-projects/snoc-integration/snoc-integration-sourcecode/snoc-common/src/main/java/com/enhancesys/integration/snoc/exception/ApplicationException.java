package com.enhancesys.integration.snoc.exception;

/**
 * <b>Purpose:</b><br>
 * 		Class to implement the process exception properties..<br>
 * <br>
 * <br>
 * <br>
 * 
 * <b>DesignReference:</b><br>
 * <br>
 * <br>
 * 
 * <b>CopyRights:</b><br>
 * 		Enhancesys Innovations 2019<br>
 * <br>
 * 
 * <b>RevisionHistory:</b>
 * 
 * <pre>
 * <b>
 *  Sl No   Modified Date        Author</b>
 *  ==============================================
 *  1        10-10-2019          Vinayak Mahadev
 *    	-- Base Release
 * </pre>
 * 
 * <br>
 */
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
