package com.enhancesys.integration.services.dataengine.util.condition.expression;

import java.util.ArrayList;

/**
 * <p>
 * This class will check if the LHS value is not among the list of RHS values.
 * <p>
 *  
 * @author Amjath Khan
 * @version 1.0
 * @since 1.0
 */
public class NotInOperator extends AbstractOperator 
{
	/**
	 * The method will evaluate is the LHS is not among the list of RHS values.
	 * 
	 * @param leftHandValue The left hand value
	 * @param rightHandValues List of right hand values
	 * @return true if LHS is not in RHS list, else false
	 * 
	 * @author Amjath Khan
	 * @version 1.0 
	 * @since 1.0
	 */
	@Override
	public boolean evaluate(String leftHandValue, ArrayList<String> rightHandValues) 
	{
		// If any of the rhs values match to the lhs value ,return true
		for(int i=0; i<rightHandValues.size();i++)
			if(leftHandValue.compareTo(rightHandValues.get(i))!=0)
				return true;
		return false;
	}

}
