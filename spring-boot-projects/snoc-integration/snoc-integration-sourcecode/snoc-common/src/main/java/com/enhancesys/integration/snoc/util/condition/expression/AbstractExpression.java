package com.enhancesys.integration.snoc.util.condition.expression;

import java.util.Map;

/**
 * <p>
 * This is a base class for all expression classes.
 * Each expression, when evaluated will result in a boolean
 * value
 * <p>
 *  
 * @author Amjath Khan
 * @version 1.0
 * @since 1.0
 */
public abstract class AbstractExpression 
{
	/**
	 * The method will evaluate the expression and return
	 * a boolean value
	 * 
	 * @param dataToEvaluate Map which contains the data for expression evaluation
	 * @return true/false
	 * 
	 * @throws EvaluationFailedException 
	 *  
	 * @author Amjath Khan
	 * @version 1.0	  
	 * @since 1.0
	 */
	public abstract boolean processExpression(Map<String, Object> dataToEvaluate) throws EvaluationFailedException; 
	
	/**
	 * The method will evaluate the expression and return
	 * a double value
	 * 
	 * @param dataToEvaluate Map which contains the data for expression evaluation
	 * @return double value
	 * 
	 * @throws EvaluationFailedException 
	 *  
	 * @author Amjath Khan
	 * @version 2.0	  
	 * @since 2.0
	 */
	public double calculateExpression(Map<String, Object> dataToEvaluate) throws EvaluationFailedException
	{
		return 0.0;
	}
	
	
	/**
	 * <p>
	 * This exception will be thrown, if the expression evaluation fails
	 * <p>
	 *  
	 * @author Amjath Khan
	 * @version 1.0
	 * @since 1.0
	 */
	@SuppressWarnings("serial")
	public class EvaluationFailedException extends Exception
	{
		/**
		 *	The constructor will take the error message as parameter
		 *
		 *	@param message			The error message passed by the thrower
		 *	@since					2.0
		 */
		public EvaluationFailedException(String message)
		{
			super(message);
		}
	}	
}
