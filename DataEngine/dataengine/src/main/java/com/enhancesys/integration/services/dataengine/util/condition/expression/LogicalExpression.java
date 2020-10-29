package com.enhancesys.integration.services.dataengine.util.condition.expression;

import java.util.Map;

/**
 * <p>
 * This is a base class for all logical expressions. 
 * Each Expression will take two of the expressions 
 * and evaluate based on the operator
 * <p>
 *  
 * @author Amjath Khan
 * @version 1.0
 * @since 1.0
 */
public class LogicalExpression extends AbstractExpression 
{
	/**
	 * The field is used to left hand expression
	 * 
	 * @author Amjath Khan
	 * @version 1.0
	 * @since 1.0
	 */
	protected AbstractExpression _leftHandExpression = null;
	
	/**
	 * The field is used to store the right hand expression
	 * 
	 * @author Amjath Khan
	 * @version 1.0
	 * @since 1.0
	 */
	protected AbstractExpression _rightHandExpression = null;
	/**
	 * The method will assign the expressions(operands) for the operator
	 * 
	 * @param rhsExpression Expression on the right hand side
	 * @param lhsExpression Expression on the left hand side
	 * @return none
	 * 
	 * @author Amjath Khan
	 * @version 1.0 
	 * @since 1.0
	 */
	public void setExpressions(AbstractExpression leftHandExpression, AbstractExpression rightHandExpression)
	{
		_leftHandExpression = leftHandExpression;
		_rightHandExpression = rightHandExpression;
	}
	
	/**
	 * The method will evaluate the expressions and return a boolean value
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
		// Changed to true to support defalut expression
		return true;
	}
}
