package com.enhancesys.integration.services.dataengine.util.condition;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import com.enhancesys.integration.services.dataengine.util.condition.expression.AbstractExpression;
import com.enhancesys.integration.services.dataengine.util.condition.expression.DataExpression;
import com.enhancesys.integration.services.dataengine.util.condition.expression.LogicalAndExpression;
import com.enhancesys.integration.services.dataengine.util.condition.expression.LogicalExpression;
import com.enhancesys.integration.services.dataengine.util.condition.expression.LogicalOrExpression;
import com.enhancesys.integration.services.dataengine.util.condition.expression.AbstractExpression.EvaluationFailedException;
import com.enhancesys.integration.services.dataengine.util.condition.expression.DataExpression.MalformedExpressionException;

/**
 * <p>
 * The class is used to evaluate a conditional expression. A complex
 * expression having logical and and or operators will be broken
 * into individual logical expressions and evaluated
 * <p>
 *  
 * @author Amjath Khan
 * @version 1.0
 * @since 1.0
 */
public class ExpressionProcessor 
{
	/**
	 * The field is used to store expression corresponding to
	 * the conditional expression. 
	 * 
	 * @author Amjath Khan
	 * @version 1.0
	 * @since 1.0
	 */
	private AbstractExpression _expression = null;
	
	/**
	 * The method is used to initialize the processor
	 * with the corresponding conditional expression. It will be expanded
	 * to form appropriate logical expressions and stored in the data member
	 * 
	 * @param conditionalExpression Conditional Expression that should be evaluated
	 * @return true/false
	 * 
	 * @author Amjath Khan
	 * @version 1.0	  
	 * @throws MalformedConditionException 
	 * @throws MalformedExpressionException 
	 * @since 1.0
	 */
	public void init(String conditionalExpression) throws MalformedConditionException, MalformedExpressionException
	{ 
		if(conditionalExpression.equals("true"))
		{
			_expression = new LogicalExpression();
			//TLogger.info("Init expression process for default value");
			return;
		}
		//TLogger.info("Init expression process: Expression "+conditionalExpression);
		// All expressions will be within () and any expression will have only two operands and an operator
		// ((([AccountDetails.Type]in['Hello'])&&([InvoiceDetails.Value]<=['1500']))||([Greetings.value]in['123','456','232']))
		
		// Stack to store the beginning index position of the expressions
		Stack<Integer> indexStack = new Stack<Integer>();
		// Stack to store the operators in the parsed segment of the condition
		Stack<String> operatorStack = new Stack<String>();
		// Stack to store the identified expressions in the parsed segment of the condition
		Stack<AbstractExpression> expressionStack = new Stack<AbstractExpression>();
	     
		// Convert into array for parsing
	    char expression[] = conditionalExpression.toCharArray();
	    
	    // Loop through the elements of the array
	    for(int i=0;i<expression.length;i++)
	    {
	    	// If beginning of an expression, push index to index stack
	    	if(expression[i]=='(')
	    		indexStack.push(i);
	    	else if(expression[i]==')') // If end of an expression
	    	{
	    		// Get the start index of the expression
    		   int startIndex = (Integer) indexStack.pop();
    		   // Push the expression to the expression stack
    		   expressionStack.push(new DataExpression(conditionalExpression.substring(startIndex+1,i)));
    		   
    		   // Check if the new expression is part of a high level expression
    		   // If the next token is a closing bracket
    		   if(i+1<expression.length && expression[i+1]==')')
    		   {
    			   // For all the successive closing brackets
    			   while(i+1<expression.length && expression[i+1]==')')
	    		   {
	    			   i++;
	    			   // Remove the corresponding opening bracket from the stack
	    			   indexStack.pop();
	    			   // If operator stack is empty
	    			   if(operatorStack.empty())
	    			   {
	    	//			   TLogger.error("High level expression with no operator "+conditionalExpression);
	    				   throw new MalformedConditionException("High level expression with no operator "+conditionalExpression);
	    			   }
	    			   else
	    			   {
	    				   LogicalExpression lexpression = null;
	    				   AbstractExpression lhsexpression = null;
	    				   AbstractExpression rhsexpression = null;
	    				   // Get the operator
	    				   String operator = operatorStack.pop();
	    				   // If and operator
	    				   if(operator.compareTo("&&")==0)
	    					   lexpression = new LogicalAndExpression();
	    				   else if(operator.compareTo("||")==0) // If or operator
	    					   lexpression = new LogicalOrExpression();
	    				   else // if neither of them, throw exception
	    				   {
	    		//			   TLogger.error("Unknown operator "+ operator + " in expression " + conditionalExpression);
	    					   throw new MalformedConditionException("Unknown operator "+ operator + " in expression " + conditionalExpression);
	    				   }
	    				   
	    				   if(expressionStack.size()<2)
	    				   {
	    			//		   TLogger.error("Not enough expressions for "+ operator + " in expression " + conditionalExpression);
	    					   throw new MalformedConditionException("Not enough expressions for "+ operator + " in expression " + conditionalExpression);
	    				   }
	    				   // First one to pop will be rhs expression
	    				   rhsexpression = expressionStack.pop();
	    				   // Second one to pop will be lhs expression
	    				   lhsexpression = expressionStack.pop();
	    				   
	    				   // set the operands for the operartor
	    				   lexpression.setExpressions(lhsexpression, rhsexpression);
	    				   
	    				   // push back to expression stack
	    				   expressionStack.push(lexpression);
	    			   }	    			   
	    		   }
    		   }
    		   // If closing bracket is not followed by another closing bracket, then it has to be the operator
    		   if(i+1<expression.length && expression[i]==')')
    		   {
    			   int j=i;
    			   // look ahead to determine the operator string
    			   while(j+1<expression.length && expression[j+1]!='(')
	    		   {
    				   j++;
	    		   }
    			   // Push the operator string to the stack
    			   operatorStack.push(conditionalExpression.substring(i+1,j+1));
    			   // reset the index to beyond operator
    			   i=j;
    		   }
    	   }
	    }
	    // both the stacks have to be empty
	    if(operatorStack.empty() && indexStack.empty())
	    {
	    	if(expressionStack.size()==1) // expression stack should have only one expression
	    	{
	    		// assign the expression to the data member
	    		_expression = expressionStack.pop();
	    	}
	    	else
	    	{
	    		throw new MalformedConditionException("Expression stack has " + expressionStack.size() + " elements for expression " + conditionalExpression);
	    	}
	    }
	    else
	    {
			//TLogger.error("Operator and index stacks are not empty ["+ operatorStack.size() + "," + indexStack.size()+ " for expression " + conditionalExpression);
	    	throw new MalformedConditionException("Operator and index stacks are not empty [" + operatorStack.size() + "," + indexStack.size() + " for expression " + conditionalExpression);
	    }
	    
	}
	
	/**
	 * The method is used to evaluate the expression with the given data
	 * 
	 * @param dataToEvaluate Map which contains the data for expression evaluation
	 * @return true/false
	 * 
	 * @author Amjath Khan
	 * @version 1.0	  
	 * @throws EvaluationFailedException 
	 * @since 1.0
	 */
	public boolean processExpression(Map<String, Object> dataToEvaluate) throws EvaluationFailedException
	{
		return _expression.processExpression(dataToEvaluate);
	}
	
	/**
	 * The method is used to process the expression with the given data
	 * 
	 * @param dataToEvaluate Map which contains the data for expression evaluation
	 * @return double
	 * 
	 * @author Amjath Khan
	 * @version 2.0	  
	 * @throws EvaluationFailedException 
	 * @since 2.0
	 */
	public double calculateExpression(Map<String, Object> dataToEvaluate) throws EvaluationFailedException
	{
		return _expression.calculateExpression(dataToEvaluate);
	}
	
	
	/**
	 * <p>
	 * This exception will be thrown, if the condition is not properly formed
	 * <p>
	 *  
	 * @author Amjath Khan
	 * @version 1.0
	 * @since 1.0
	 */
	@SuppressWarnings("serial")
	public class MalformedConditionException extends Exception
	{
		/**
		 *	The constructor will take the error message as parameter
		 *
		 *	@param message			The error message passed by the thrower
		 *	@since					2.0
		 */
		public MalformedConditionException(String message)
		{
			super(message);
		}
	}
	
	public static void main(String args[])
	{
		ExpressionProcessor p = new ExpressionProcessor();
		try {
			
//			p.init("(([registeredRoleType]IN['953'])&&([initiatorRoleType]IN['93']))");
			
			p.init("([registeredRoleType]IN['953'])");
			
			Map<String,Object> dataToEvaluate = new HashMap<String,Object>();
			dataToEvaluate.put("registeredRoleType", "953");
			dataToEvaluate.put("initiatorRoleType", "903");
			
			System.out.println(p.processExpression(dataToEvaluate));
			
			
		} catch (MalformedConditionException e) {
			e.printStackTrace();
		} catch (MalformedExpressionException e) {
			e.printStackTrace();
		} catch (EvaluationFailedException e) {
			e.printStackTrace();
		}
	}
}
