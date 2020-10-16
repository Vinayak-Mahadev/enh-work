package enh.team.interfaces.exception;

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
