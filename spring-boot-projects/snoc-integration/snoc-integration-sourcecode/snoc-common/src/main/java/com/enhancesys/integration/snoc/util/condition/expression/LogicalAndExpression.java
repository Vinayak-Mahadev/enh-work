package com.enhancesys.integration.snoc.util.condition.expression;

import java.util.Map;

/**
 * <p>
 * This class is used to perform logical and on the
 * two other expressions
 * <p>
 *  
 * @author Amjath Khan
 * @version 1.0
 * @since 1.0
 */
public class LogicalAndExpression extends LogicalExpression
{
	/**
	 * The method will evaluate the lhs and rhs expressions
	 * 
	 * @param dataToEvaluate Map which contains the data for expression evaluation
	 * @return true/false
	 * 
	 * @throws EvaluationFailedException
	 * @author Amjath Khan
	 * @version 1.0 
	 * @since 1.0
	 */
	@Override
	public boolean processExpression(Map<String, Object> dataToEvaluate) throws EvaluationFailedException 
	{
		// If lhs is success
		if(_leftHandExpression.processExpression(dataToEvaluate))
			if(_rightHandExpression.processExpression(dataToEvaluate)) // and rhs is success
				return true; 
		return false;
	}
}
