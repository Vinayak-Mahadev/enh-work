package com.enhancesys.jobcommon.condition.expression;

import java.util.ArrayList;

/**
 * <p>
 * This is a base class for all operators used in the 
 * boolean expressions. The derived class instances will
 * be used to perform the desired operation
 * <p>
 *  
 * @author Amjath Khan
 * @version 1.0
 * @since 1.0
 */
public abstract class AbstractOperator 
{
	/**
	 * The method will evaluate the LHS and RHS values and
	 * return appropriate boolean value
	 * 
	 * @param leftHandValue The right hand value
	 * @param rightHandValues List of left hand values
	 * @return true/false
	 * 
	 * @author Amjath Khan
	 * @version 1.0 
	 * @since 1.0
	 */
	public abstract boolean evaluate(String leftHandValue, ArrayList<String> rightHandValues);
	
	/**
	 * The method will perform mathematical operation on the LHS and RHS values and
	 * return appropriate value
	 * 
	 * @param leftHandValue The left hand value
	 * @param rightHandValues List of right hand values
	 * @return true/false
	 * 
	 * @author Amjath Khan
	 * @version 2.0 
	 * @since 2.0
	 */
	public double calculate(String leftHandValue, String rightHandValue)
	{
		return 0.0;
	}
	
	/**
	 * <p>
	 * This exception will be thrown, if there are any issues in evaluation
	 * <p>
	 *  
	 * @author Amjath Khan
	 * @version 1.0
	 * @since 1.0
	 */
	@SuppressWarnings("serial")
	public class EvaluationException extends Exception
	{
		/**
		 *	The constructor will take the error message as parameter
		 *
		 *	@param message			The error message passed by the thrower
		 *	@since					2.0
		 */
		public EvaluationException(String message)
		{
			super(message);
		}
	}
}
