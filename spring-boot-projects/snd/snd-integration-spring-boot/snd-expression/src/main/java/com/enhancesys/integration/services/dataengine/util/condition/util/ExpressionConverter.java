package com.enhancesys.integration.services.dataengine.util.condition.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpressionConverter {

	public String convertExpression(String expression) throws Exception
	{

		String returnExpression = null;
		try
		{

			//		expression = "axy*b*(c*(d+f))*(floor)(e*f/2)";


			//Operator's are predefined
			List<String> operators = new ArrayList<String>();
			operators.add("*");
			operators.add("+");
			operators.add("-");
			operators.add("/");
			operators.add("%");

			//		System.out.println("exp >>>" + expression);

			Map<String, String> map = new HashMap<String, String>();
			map.put("exp", expression);

			//Parse expression into a sub expression
			parseExpression("exp", map);


			//Convert expression into specify format
			for(String key : map.keySet())
			{
				String value = map.get(key);
				StringBuilder  newValue = new StringBuilder();
				boolean isFirstOperator = false;
				newValue.append("(");
				for(int i = 0 ; i < value.length() ; i++)
				{
					char letter = value.charAt(i);

					if(operators.contains(""+letter))
					{
						if(isFirstOperator)
						{
							newValue.insert(0, "(");
							newValue.append(")");
						}

						isFirstOperator = true;
					}

					newValue.append(""+letter);
				}
				newValue.append(")");

				map.put(key, newValue.toString());
			}


			//Join all subexpression into one
			returnExpression = joinExpression("exp", map);

			System.out.println("Return expression " +returnExpression);

		}
		catch(Exception e)
		{
			throw e;
		}
		return returnExpression;
	}


	private String joinExpression(String key, Map<String, String> map)
	{

		String returnString =  map.get(key);
		int startIndex = -1;
		int count = 0;

		//		System.out.println( " key" +key +", " + map.get(key));

		List<String> keyList = new ArrayList<String>();

		for(int i = 0 ; i < returnString.length() ; i++)
		{
			char letter = returnString.charAt(i);

			if('#'==letter)
			{
				count++;

				if(startIndex==-1)
					startIndex = i;
			}

			if(count==2)
			{
				String newKey = returnString.substring(startIndex+1, i);
				keyList.add("#"+newKey+"#");
				startIndex = -1;
				count = 0;
			}
		}

		for(String newKey : keyList)
		{
			String returnValue = joinExpression(newKey, map);
			returnString = returnString.replace(newKey, returnValue);
		}
		return returnString;

	}

	private void parseExpression(String key, Map<String, String> map) throws Exception
	{
		String exp = map.get(key);

		//		System.out.println("Map "+ map);
		//		System.out.println("Key :: " + key +" Exp ::" + exp);

		int startIndex = -1;
		int endIndex = startIndex;
		int noOfOpenBraces = 0;
		boolean isBracesExist = false;
		for(int i = 0 ; i < exp.length() ; i++)
		{
			char letter = exp.charAt(i);

			//			System.out.println(letter);

			if('('==letter)
			{
				noOfOpenBraces++;

				isBracesExist = true;

				if(startIndex==-1)
					startIndex = i;
			}

			if(')'==letter)
			{
				noOfOpenBraces--;
				endIndex = i ;
			}


			if(noOfOpenBraces==0 && isBracesExist)
			{
				//				System.out.println("startIndex :: "+ startIndex +" endIndex :: " + endIndex);
				String newKey = "#"+i+"~"+System.currentTimeMillis()+"#";
				String newExp = exp.substring(startIndex+1, endIndex);
				map.put(newKey, newExp.toString());
				parseExpression(newKey, map);

				exp = exp.replace("("+newExp+")", newKey);

				startIndex=-1;
				endIndex=0;
				isBracesExist = false;
			}
		}
		if(noOfOpenBraces!=0)
		{
			throw new Exception("Invalid Expression - Invalid open and close braceses");
		}
		map.put(key, exp);

	}

	public static void main(String[] args) {

		try{
			ExpressionConverter expressionConverter = new ExpressionConverter();

			String exp = "axy*b*(c*(d+f))*(floor)(e*f/2)";

			System.out.println(expressionConverter.convertExpression(exp));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}
}
