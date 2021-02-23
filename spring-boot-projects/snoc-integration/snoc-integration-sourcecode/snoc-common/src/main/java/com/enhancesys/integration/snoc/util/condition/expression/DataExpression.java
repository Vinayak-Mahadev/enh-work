package com.enhancesys.integration.snoc.util.condition.expression;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * This class is used to evaluate a boolean expression. The left hand side
 * of the expression will be of format [KeyNameInMap] and the right
 * hand side can contain one ore more values delimited by comma ['val1','val2']
 * The operators include less than, greater than, greater than or equal to,
 * less than or equal to, equal to, not equal to, IN and NOTIN
 * <p>
 *  
 * @author Amjath Khan
 * @version 1.0
 * @since 1.0
 */
public class DataExpression extends AbstractExpression 
{
	/**
	 * The field is used to store the map key name
	 * 
	 * @author Amjath Khan
	 * @version 1.0
	 * @since 1.0
	 */
	private String _mapKeyName;
	
	/**
	 * The field is used to store the right hand operand
	 * 
	 * @author Amjath Khan
	 * @version 2.0
	 * @since 2.0
	 */
	private String _rightHandOperand;
	
	/**
	 * The field is used to store a list of expected values
	 * 
	 * @author Amjath Khan
	 * @version 1.0
	 * @since 1.0
	 */
	private ArrayList<String> _expectedValues = new ArrayList<String>();
	
	/**
	 * The field is used to store a operator that will evaluate the expression
	 * 
	 * @author Amjath Khan
	 * @version 1.0
	 * @since 1.0
	 */
	private AbstractOperator _operator = null;

	/**
	 * The constructor will take the boolean expression as an input
	 * 
	 * @param expression The boolean expression
	 * @return An instance of DataExpression object
	 * 
	 * @author Amjath Khan
	 * @version 1.0
	 * @throws MalformedExpressionException 
	 * @since 1.0
	 */
	public DataExpression(String expression) throws MalformedExpressionException
	{
		// Expression should always be of format [DataSetName.ColumnName]operator[Value-List]
		//TLogger.info("Spool Data Expression "+expression);
		if(!expression.startsWith("["))
			throw new MalformedExpressionException("Expression not starting with [ :" + expression);
		// Split the expression based on the square brackets. substring done to avoid the empty first element due to split
		String tokens[] = expression.substring(1).split("[\\[\\]]");
		
		// It should return an array of 3 elements, else the expression is not correct
		if(tokens.length != 3)
		{
			//TLogger.error("Expression not as per syntax :" + expression);
			throw new MalformedExpressionException("Expression not as per syntax :" + expression);
		}
		
		_mapKeyName = tokens[0];
		
		tokens[1] = tokens[1].trim();
		
		// Based on the operator assign the corresponding object to evaluate the expression
		if(tokens[1].compareTo("<")==0)
			_operator = new LessThanOperator();
		else if(tokens[1].compareTo("<=")==0)
			_operator = new LessThanOrEqualOperator();
		else if(tokens[1].compareTo(">")==0)
			_operator = new GreaterThanOperator();
		else if(tokens[1].compareTo(">=")==0)
			_operator = new GreaterThanOrEqualOperator();
		else if(tokens[1].compareTo("IN")==0)
			_operator = new InOperator();
		else if(tokens[1].compareTo("NOTIN")==0)
			_operator = new NotInOperator();
		else if(tokens[1].compareTo("*")==0)
			_operator = new MultiplicationOperator();
		else
		{
		//	TLogger.error("Unknown Operator used :"+tokens[1]);
			throw new MalformedExpressionException("Unknown Operator used :"+tokens[1]);
		}
		
		// Values will be delimited by comma
		String values[] = tokens[2].split(",");
		
		if(values.length==1 && !(values[0].startsWith("'")&&values[0].endsWith("'")))
		{
			_rightHandOperand = values[0];
		}
		else
		{
			
			// Loop thorugh the extracted values
			for(int i=0; i<values.length; i++)
			{
				values[i] = values[i].trim();
				// If the value starts and ends with a single quote
				if(values[i].startsWith("'")&&values[i].endsWith("'"))
				{
					// Trim the quotes and add the value to the list
					_expectedValues.add(values[i].substring(1,values[i].length()-1));
				}
				else
				{
				//	TLogger.error("Value not as per syntax :"+values[i]);
					throw new MalformedExpressionException("Value not as per syntax :"+values[i]);
				}
			}
		}
	}
	
	/**
	 * The method will extract the required value from the Data Source
	 * and perform the desired operation
	 * 
	 * @param dataToEvaluate Map which contains the data for expression evaluation
	 * @return true/false
	 * 
	 * @author Amjath Khan
	 * @version 1.0	  
	 * @throws EvaluationFailedException 
	 * @since 1.0
	 */
	@SuppressWarnings("unchecked")
	public boolean processExpression(Map<String, Object> dataToEvaluate) throws EvaluationFailedException
	{
		//TLogger.info("Process expression of spool data expression");
		try 
		{
			if(dataToEvaluate.containsKey(_mapKeyName))
			{

				if(dataToEvaluate.get(_mapKeyName) instanceof List)
				{
					List<String> dataListToEvaluate = (List<String>) dataToEvaluate.get(_mapKeyName);
					boolean evaluationResult = false;
					for(String dataValueToEvaluate : dataListToEvaluate)
					{
						evaluationResult = _operator.evaluate(dataValueToEvaluate,_expectedValues);
						if(evaluationResult)
							return evaluationResult;
					}
					
					return evaluationResult;
				}else{
					return _operator.evaluate(dataToEvaluate.get(_mapKeyName).toString(),_expectedValues);
				}
			}
			else
				return false;
		} 
		catch (Exception e) 
		{
			//TLogger.error(e.getMessage());
			throw new EvaluationFailedException(e.getMessage());
		} 		
	}
	
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
		//TLogger.info("Process expression of spool data expression");
		try 
		{
			if(dataToEvaluate.containsKey(_mapKeyName))
			{
				return _operator.calculate(dataToEvaluate.get(_mapKeyName).toString(),dataToEvaluate.get(_rightHandOperand).toString());
			}
			else
				return 0.0;
		} 
		catch (Exception e) 
		{
			//TLogger.error(e.getMessage());
			throw new EvaluationFailedException(e.getMessage());
		}
	}
	
	/**
	 * <p>
	 * This exception will be thrown, if the expression is not properly formed
	 * <p>
	 *  
	 * @author Amjath Khan
	 * @version 1.0
	 * @since 1.0
	 */
	@SuppressWarnings("serial")
	public class MalformedExpressionException extends Exception
	{
		/**
		 *	The constructor will take the error message as parameter
		 *
		 *	@param message			The error message passed by the thrower
		 *	@since					2.0
		 */
		public MalformedExpressionException(String message)
		{
			super(message);
		}
	}
		
}
