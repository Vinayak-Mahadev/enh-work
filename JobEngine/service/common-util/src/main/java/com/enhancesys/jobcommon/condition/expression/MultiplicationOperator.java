package com.enhancesys.jobcommon.condition.expression;

import java.util.ArrayList;

/**
 * <p>
 * This class will check if the LHS value is less than the RHS value.
 * Only the first value of the RHS is considered
 * <p>
 *  
 * @author Amjath Khan
 * @version 1.0
 * @since 1.0
 */
public class MultiplicationOperator extends AbstractOperator 
{
	/**
	 * The method will multiply LHS and RHS
	 * 
	 * @param leftHandValue The left hand value
	 * @param rightHandValue List of left hand values
	 * @return multiplied value
	 * 
	 * @author Amjath Khan
	 * @version 2.0 
	 * @since 2.0
	 */
	@Override
	public double calculate(String leftHandValue, String rightHandValue) 
	{
		try
		{
			// Check if the values are integer values. If so perform integer comparision
			double lhs = Double.parseDouble(leftHandValue);
			double rhs = Double.parseDouble(rightHandValue);
			
			return (lhs * rhs);
		}
		catch(NumberFormatException e)
		{
			; // Ignore the exception
		}
		
		// Else compare string comparision
		return 0;		
	}

	@Override
	public boolean evaluate(String leftHandValue, ArrayList<String> rightHandValues) 
	{
		// TODO Auto-generated method stub
		return false;
	}

}
