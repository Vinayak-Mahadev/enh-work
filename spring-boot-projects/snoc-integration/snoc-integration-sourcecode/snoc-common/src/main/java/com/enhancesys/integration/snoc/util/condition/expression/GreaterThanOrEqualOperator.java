package com.enhancesys.integration.snoc.util.condition.expression;

import java.util.ArrayList;

/**
 * <p>
 * This class will check if the LHS value is greater than or equal to the RHS value.
 * Only the first value of the RHS is considered
 * <p>
 *  
 * @author Amjath Khan
 * @version 1.0
 * @since 1.0
 */
public class GreaterThanOrEqualOperator extends AbstractOperator 
{
	/**
	 * The method will evaluate is the LHS is greater than or equal to RHS
	 * 
	 * @param leftHandValue The right hand value
	 * @param rightHandValues List of left hand values
	 * @return true if LHS is greater than or equal to RHS, else false
	 * 
	 * @author Amjath Khan
	 * @version 1.0 
	 * @since 1.0
	 */
	@Override
	public boolean evaluate(String leftHandValue, ArrayList<String> rightHandValues) 
	{
		try
		{
			// Check if the values are integer values. If so perform integer comparision
			int lhs = Integer.parseInt(leftHandValue);
			int rhs = Integer.parseInt(rightHandValues.get(0));
			
			return ( lhs >= rhs );
		}
		catch(NumberFormatException e)
		{
			; // Ignore the exception
		}
		
		// Else compare string comparision
		return (leftHandValue.compareTo(rightHandValues.get(0)) >= 0);		
	}
}