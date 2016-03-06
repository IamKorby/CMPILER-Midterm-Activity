/*
 * Algorithm based from: www.sunshine2k.de/coding/java/SimpleParser/SimpleParser.html
 */
package controller;

import java.util.ArrayList;
import java.util.Stack;

import model.ErrorType;
import model.TokenNode;
import model.TokenType;
import model.TokenizedInput;

public class Evaluator
{
	private static boolean hasError;
	
	public static void evaluate(TokenizedInput input)
	{
		ArrayList<TokenNode> tokens = input.getPostfixTokens();
		Stack<TokenNode> stack = new Stack<TokenNode>();
		int currentPosition = 0, x, y, result;
		hasError = false;
		
		//3 5 + 2 * 6 3 - +
		
		while( currentPosition < tokens.size() )
		{
			if( !hasError )
			{
				if( tokens.get(currentPosition).getTokenType() == TokenType.OPERAND )
				{
					stack.push(tokens.get(currentPosition));
				}
				else if( tokens.get(currentPosition).getTokenType() == TokenType.OPERATOR ||
					    tokens.get(currentPosition).getTokenType() == TokenType.OPERATOR_UNARY )
				{
					x = 0;
					y = 0;
					
					if( !stack.empty() )
					{
						try
						{
							x = Integer.parseInt(stack.pop().getToken());
						}
						catch( NumberFormatException nf )
						{
							input.setErrorType(ErrorType.VALUE_EXCEED_INTEGER_LIMIT);
							hasError = true;
						}
//						isExceedInteger(input, x);
					}
					
					if( !stack.empty() )
					{
						try
						{
							y = Integer.parseInt(stack.pop().getToken());
						}
						catch( NumberFormatException nf )
						{
							input.setErrorType(ErrorType.VALUE_EXCEED_INTEGER_LIMIT);
							hasError = true;
						}
//						isExceedInteger(input, y);
					}
					
					if( tokens.get(currentPosition).getToken() == "+" )
					{
						y += x;
					}
					else if( tokens.get(currentPosition).getToken() == "-" )
					{
						y -= x;
					}
					else if( tokens.get(currentPosition).getToken() == "*" )
					{
						y *= x;
					}
					else if( tokens.get(currentPosition).getToken() == "/" )
					{
						try
						{
							y /= x;
						}
						catch( ArithmeticException ae )
						{
							input.setErrorType(ErrorType.ARITHMETIC_EXCEPTION_DIVIDE_BY_ZERO_ERROR);
							hasError = true;
						}
					}
					else if( tokens.get(currentPosition).getToken() == "%" )
					{
						try
						{
							y %= x;
						}
						catch( ArithmeticException ae )
						{
							input.setErrorType(ErrorType.ARITHMETIC_EXCEPTION_DIVIDE_BY_ZERO_ERROR);
							hasError = true;
						}
					}
					
					stack.push(new TokenNode(Integer.toString(y), TokenType.OPERAND));
				}
				
				currentPosition++;
			}
			else
			{
				currentPosition = tokens.size() + 1;
			}
		}
		
		if( !hasError )
		{
			try
			{
				input.setValue(Integer.parseInt(stack.pop().getToken()));
			}
			catch( NumberFormatException nf )
			{
				input.setErrorType(ErrorType.VALUE_EXCEED_INTEGER_LIMIT);
				hasError = true;
			}
		}
	}
	
//	private static void isExceedInteger( TokenizedInput input, int value )
//	{
//		if( value > Integer.MAX_VALUE )
//		{
//			input.setErrorType(ErrorType.INTEGER_OVERFLOW);
//			hasError = true;
//		}
//		else if( value < Integer.MIN_VALUE )
//		{
//			input.setErrorType(ErrorType.INTEGER_UNDERFLOW);
//			hasError = true;
//		}
//	}
}
