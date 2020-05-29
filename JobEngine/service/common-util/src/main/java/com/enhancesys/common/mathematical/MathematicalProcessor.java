package com.enhancesys.common.mathematical;

import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import com.enhancesys.common.condition.expression.DataExpression.MalformedExpressionException;
import com.jayway.jsonpath.JsonPath;

/**
 * <p>
 * The class is used to evaluate a mathematical expression. 
 * <p>
 *  
 * @author Amjath Khan
 * @version 2.0
 * @since 2.0
 */
public class MathematicalProcessor 
{
	
	/**
	 * The field is used to store the list of mathematical operators. 
	 * 
	 * @author Amjath Khan
	 * @version 2.0
	 * @since 2.0
	 */
	@SuppressWarnings("unchecked")
	private static final Set<String> _binaryOperatorSet = new HashSet<String>(Arrays.asList("+","-","*","/","%"));
	
	/**
	 * The field is used to store the list of mathematical functions. 
	 * 
	 * @author Amjath Khan
	 * @version 2.0
	 * @since 2.0
	 */
	@SuppressWarnings("unchecked")
	private static final Set<String> _unaryOperatorSet = new HashSet<String>(Arrays.asList("round","ceil","floor"));
	
	/**
	 * Stack to store the beginning index position of the expressions 
	 * 
	 * @author Amjath Khan
	 * @version 2.0
	 * @since 2.0
	 */
	Stack<Integer> _indexStack = new Stack<Integer>();

	/**
	 * Stack to store the parsed operators 
	 * 
	 * @author Amjath Khan
	 * @version 2.0
	 * @since 2.0
	 */	
	Stack<String> _operatorStack = new Stack<String>();
	
	/**
	 * Stack to store the parsed expressions 
	 * 
	 * @author Amjath Khan
	 * @version 2.0
	 * @since 2.0
	 */	
	Stack<String> _expressionStack = new Stack<String>();
			
	/**
	 * The method is used to check if the given token is an unary operator or not
	 * 
	 * @param token String token that needs to be evaluated
	 * @return true
	 * 
	 * @author Amjath Khan
	 * @version 2.0	   
	 * @since 2.0
	 */
	private boolean isUnaryOperator(String token)
	{
		if(_unaryOperatorSet.contains(token))
			return true;
		return false;
	}
	
	/**
	 * The method is used to check if the given token is a binary operator or not
	 * 
	 * @param token String token that needs to be evaluated
	 * @return true
	 * 
	 * @author Amjath Khan
	 * @version 2.0	   
	 * @since 2.0
	 */
	private boolean isBinaryOperator(String token)
	{
		if(_binaryOperatorSet.contains(token))
			return true;
		return false;
	}
	
	/**
	 * The method is used to perform unary operation
	 * 
	 * @param rhs right hand operand
	 * @param operator operator
	 * @return result of unary operation on the operand
	 * 
	 * @author Amjath Khan
	 * @version 2.0	   
	 * @since 2.0
	 */
	private String performUnaryOperation(String rhs, String operator)
	{
		double result = 0.0;
		// If round
		if(operator.equals("round"))
		{
			result = Math.round(Double.parseDouble(rhs));
		}
		else if(operator.equals("ceil")) //If ceil
		{
			result = Math.ceil(Double.parseDouble(rhs));
		}
		else if(operator.equals("floor")) //If floor
		{
			result = Math.floor(Double.parseDouble(rhs));
		}
		return String.valueOf(result);			
	}
	
	/**
	 * The method is used to perform binary operation
	 * 
	 * @param rhs right hand operand
	 * @param operator operator
	 * @param lhs left hand operand
	 * @return result of binary operation on the operand
	 * 
	 * @author Amjath Khan
	 * @version 2.0	   
	 * @since 2.0
	 */
	private String performBinaryOperation(String lhs, String operator, String rhs)
	{
		// Get the lhs operand
		double left = Double.parseDouble(lhs);
		// Get the rhs operand
		double right = Double.parseDouble(rhs);
		double result = 0.0;
		// Get the operator
		switch(operator.charAt(0))
		{
			case '+' : result = left + right; break;
			case '-' : result = left - right; break;
			case '*' : result = left * right; break;
			case '/' : result = left / right; break;
			case '%' : result = left % right; break;
		}
		return String.valueOf(result);			
	}
	
	/**
	 * The method is used to validate if the given string is numeric or not
	 * 
	 * @param str string to be evaluated
	 * @return true/false
	 * 
	 * @author Amjath Khan
	 * @version 2.0 
	 * @since 2.0
	 */
	public static boolean isStringNumeric( String str )
	{
		// copied from http://stackoverflow.com/questions/1102891/how-to-check-if-a-string-is-numeric-in-java
	    DecimalFormatSymbols currentLocaleSymbols = DecimalFormatSymbols.getInstance();
	    char localeMinusSign = currentLocaleSymbols.getMinusSign();

	    if ( !Character.isDigit( str.charAt( 0 ) ) && str.charAt( 0 ) != localeMinusSign ) return false;

	    boolean isDecimalSeparatorFound = false;
	    char localeDecimalSeparator = currentLocaleSymbols.getDecimalSeparator();

	    for ( char c : str.substring( 1 ).toCharArray() )
	    {
	        if ( !Character.isDigit( c ) )
	        {
	            if ( c == localeDecimalSeparator && !isDecimalSeparatorFound )
	            {
	                isDecimalSeparatorFound = true;
	                continue;
	            }
	            return false;
	        }
	    }
	    return true;
	}
	
	/**
	 * The method is used to extract and push expressions/operators into appropriate stack
	 * 
	 * @param mathematicalExpression Mathematical expression that has to be evaluated
	 * @param currentIndex current position of parsing
	 * @param jsonData jsonData will have the data corresponding to the variables used in the expression
	 * @return void
	 * 
	 * @author Amjath Khan
	 * @version 2.0	  
	 * @throws ParseException 
	 * @throws MalformedMathematicalException 
	 * @throws MalformedExpressionException 
	 * @since 2.0
	 */
	void parseExpression(String mathematicalExpression, int currentIndex, String jsonData) throws ParseException
	{
		// starting position of the expression token
		int startIndex = 0;
		// expression token
		String token = null;
		
		// get the start position from the stack
		startIndex = (Integer) _indexStack.pop();
		// extract the token string
		token = mathematicalExpression.substring(startIndex+1,currentIndex);
		
		// verify if the token is an operator
		if(isUnaryOperator(token)||isBinaryOperator(token))
		{
			// Add the operator to the operator stack
			_operatorStack.add(token);
			//if(isUnaryOperator(token)&&!_indexStack.isEmpty())
			//if(isUnaryOperator(token))
			//	_indexStack.pop();
			//System.out.println(currentIndex+"-operator->"+token);
		}
		else
		{
			if(isStringNumeric(token)) //if constant numeric value
				_expressionStack.add(token); // add the operand to the stack
			else // If variable, extract the value from the json data
			{
				// Add value of the variable to the stack
				_expressionStack.add(JsonPath.read(jsonData, token).toString());
				//System.out.println(currentIndex+"-expression->"+JsonPath.read(jsonData, token));
			}
		}		
	}
	
	
	/**
	 * The method is used to process the expression
	 * 
	 * @param currentIndex index of the current position
	 * @return void
	 * 
	 * @author Amjath Khan
	 * @version 2.0 
	 * @throws MalformedMathematicalException 
	 * @since 2.0
	 */
	void evaulateExpression(int currentIndex) throws MalformedMathematicalException
	{
		// Get the operator on the top of the stack
		String operator = _operatorStack.pop();
		String result = null;
		String rhs = null;
		String lhs = null;
	   
		// If the operator is unary
		if(isUnaryOperator(operator))
		{
			// If no operand available, notify
			if(_expressionStack.empty())
				throw new MalformedMathematicalException("High level expression with no operator at "+currentIndex);
			// Get one operand from the operand stack
			rhs = _expressionStack.pop();
			//System.out.println(currentIndex+"--operation->"+rhs+":"+operator);
			// Perform the unary operation on the operand
			result = performUnaryOperation(rhs,operator);
		}
		else
		{
			// If no operand available, notify
			if(_expressionStack.empty())
				throw new MalformedMathematicalException("High level expression with no operator at "+currentIndex);
			// Get the right hand operand
			rhs = _expressionStack.pop();
			
			// If no operand available, notify
			if(_expressionStack.empty())
				throw new MalformedMathematicalException("High level expression with no operator at "+currentIndex);
			// Get the left hand operand
			lhs = _expressionStack.pop();
			
			// Perform the binary operation
			result = performBinaryOperation(lhs,operator,rhs);
			//System.out.println(currentIndex+"---operation->"+lhs+":"+operator+":"+rhs);
		}
		//System.out.println(currentIndex+"---expression->"+result);
		// push back the calculated value to expression stack
		_expressionStack.push(result);
	}
	
	/**
	 * The method is used to evaluate a mathematical expression
	 * 
	 * @param mathematicalExpression Mathematical expression that has to be evaluated
	 * @param jsonData jsonData will have the data corresponding to the variables used in the expression
	 * @return double
	 * 
	 * @author Amjath Khan
	 * @version 2.0	  
	 * @throws MalformedMathematicalException 
	 * @throws ParseException 
	 * @throws MalformedExpressionException 
	 * @since 2.0
	 */
	public double process(String mathematicalExpression, String jsonData) throws MalformedMathematicalException, ParseException
	{ 
		if(mathematicalExpression == null)
		{
			throw new MalformedMathematicalException("Given expression is null");
		}
		
		double resultValue = 0.0;
		
		// Convert into array for parsing
	    char expression[] = mathematicalExpression.toCharArray();
	    
	    // By default, push a dummy to handle simple expressions without any ()
	    _indexStack.push(-1);
	    
	    // Loop through the elements of the array
	    for(int i=0;i<expression.length;i++)
	    {
	    	//System.out.print(expression[i]);
	    	//System.out.print("::index"+_indexStack.toString());
	    	//System.out.print("::operator"+_operatorStack.toString());
	    	//System.out.println("expression"+_expressionStack.toString());
	    	
	    	// If beginning of an expression, push index to index stack
	    	if(expression[i]=='(')
	    	{
	    		// If ( found at the beginning of the expression, remove the dummy
	    		if(i==0)
	    			_indexStack.pop();
	    		_indexStack.push(i);
	    	}
	    	else if(expression[i]==')') // If end of an expression
	    	{
	    		// If token start is not available
	    		if(_indexStack.empty())
	    			throw new MalformedMathematicalException("High level expression at "+i+" with no operator "+mathematicalExpression);
    			
	    		// If there is no immediate ) before the current one, it indicates that a token has been identified
	    		if(!(expression[i-1]==')'))
	    			parseExpression(mathematicalExpression,i,jsonData);
	    		else // If not, then the current ) indicates the closure of a bigger expression
	    		{
	    			// Check if there is a unary operator on top of the stack. If so, evaluate the expression
	    			if(!_operatorStack.isEmpty()&&isUnaryOperator(_operatorStack.peek()))
	    				evaulateExpression(i);
	    			// remove the opening position of the bigger expression from stack
	    			_indexStack.pop();
	    		}
	    		
	    		// check if the top of the operator stack is a binary operator, evaluate the expression
	    		if(!_operatorStack.isEmpty()&&isBinaryOperator(_operatorStack.peek()))
	    		{
	    			evaulateExpression(i);
	    		}
	    	}
		    else // If any other character
	    	{
	    		switch(expression[i]) // If its a mathematical operator
	    		{
	    			case '+':
	    			case '-':
	    			case '*':
	    			case '/':
	    			case '%':			
	    					// If the last character is a closing bracket, then the lhs value will already be available in stack. If not, get the value of the variable
	    					if(!(expression[i-1]==')'))
	    						parseExpression(mathematicalExpression,i,jsonData);
	    					
	    					// Push the operator to the stack
	    	    			_operatorStack.push(mathematicalExpression.substring(i, i+1));
	    	    			
	    	    			// If not end of expression, then push the starting postion of the next token
	    	    			if(i!=expression.length-1)
	    	    				_indexStack.push(i);
	    	    			break;
	    					
	    		}
	    	}
	    }	    
	    
	    //System.out.println(_indexStack.size());
	    /*if(_indexStack.size() >= 1 && )
	    {
	    	// Get the start index of the expression
	    	// parse the extracted expression
    		parseExpression(mathematicalExpression,mathematicalExpression.length(),jsonData);
	    }*/
	    
	    //System.out.println(_operatorStack.size()+":"+_indexStack.size()+":"+_expressionStack.size());
	    
	    // both the stacks have to be empty
	    if(_operatorStack.empty() && _indexStack.empty())
	    {
	    	if(_expressionStack.size()==1) // expression stack should have only one expression
	    	{
	    		//evaulateExpression(mathematicalExpression.length());
	    		// assign the expression to the data member
	    		resultValue = Double.parseDouble(_expressionStack.pop());
	    	}
	    	else
	    	{
	    		throw new MalformedMathematicalException("Expression stack has " + _expressionStack.size() + " elements for expression " + mathematicalExpression);
	    	}
	    }
	    else
	    {
	    	// Till there are operators and operands available
	    	while(_expressionStack.size() >= 1 && !_operatorStack.empty())
	    	{
	    		// evaluate the expression
				evaulateExpression(mathematicalExpression.length());
	    	}
	    	resultValue = Double.parseDouble(_expressionStack.pop());
		}
	    return resultValue;
	    
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
	public class MalformedMathematicalException extends Exception
	{
		/**
		 *	The constructor will take the error message as parameter
		 *
		 *	@param message			The error message passed by the thrower
		 *	@since					2.0
		 */
		public MalformedMathematicalException(String message)
		{
			super(message);
		}
	}
	
	public static void main(String args[]) throws ParseException
	{
		MathematicalProcessor p = new MathematicalProcessor();
		try {
//			String jsonData = "{\"orderQuantity\":100,\"unitPrice\":100.5436}";
			String jsonData = "{\"orderQuantity\":100.3,\"unitPrice\":100.54,\"discount\":12.43}";
			//String expression = "(orderQuantity*unitPrice)";
			//String expression = "((round)(orderQuantity*unitPrice))";
			//String expression = "(orderQuantity*(unitPrice+2))";
			//String expression = "((floor)(orderQuantity*(unitPrice+2)))";
			//String expression = "((orderQuantity*unitPrice)+2)";
			//String expression = "(2+(orderQuantity*unitPrice))";
//			String expression = "((ceil)(2+(orderQuantity*unitPrice)))";
			//String expression = "((ceil)((orderQuantity*unitPrice)+2))";
			//String expression = "((orderQuantity*1)*(unitPrice*1.5))";
			//String expression = "((round)((orderQuantity*1)*(unitPrice*1.5)))";
			//String expression = "((200-198)*((floor)(orderQuantity/unitPrice)))";
			//String expression = "(((round)(orderQuantity*unitPrice))/(200-198))";
			//String expression = "(((round)(orderQuantity*unitPrice))/(2%2))";
			
//			String expression = "((200-198.2)/((round)(orderQuantity*unitPrice)))"; //Working
//			String expression = "((round)(((round)(orderQuantity*unitPrice))/(200-198.2)))"; 
			String expression = "(((round)(orderQuantity*unitPrice))/(0))"; // 
			
//			String expression = "((((((round)(orderQuantity*unitPrice))*((discount-12.43)/100))))+((round)(orderQuantity*unitPrice)))"; 
			
			System.out.println(p.process(expression,jsonData));
		} catch (MalformedMathematicalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
