package controller;

import java.util.ArrayList;
import java.util.Stack;

import model.ErrorType;
import model.TokenNode;
import model.TokenType;
import model.TokenizedInput;

public class Evaluator
{
	public static void evaluate(TokenizedInput input)
	{
		ArrayList<TokenNode> tokens = input.getPostfixTokens();
		Stack<TokenNode> stack = new Stack<TokenNode>();
		int currentPosition = 0;
		boolean hasError = false;
		
		//3 5 + 2 * 6 3 - +
		
		while( currentPosition < tokens.size() )
		{
			if( tokens.get(currentPosition).getTokenType() == TokenType.OPERAND )
			{
				stack.push(tokens.get(currentPosition));
			}
			else if( tokens.get(currentPosition).getTokenType() == TokenType.OPERATOR )
			{
				int x = Integer.parseInt(stack.pop().getToken());
				int y = Integer.parseInt(stack.pop().getToken());
				
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
					}catch( ArithmeticException ae )
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
					}catch( ArithmeticException ae )
					{
						input.setErrorType(ErrorType.ARITHMETIC_EXCEPTION_DIVIDE_BY_ZERO_ERROR);
						hasError = true;
					}
				}
				
				stack.push(new TokenNode(Integer.toString(y), TokenType.OPERAND));
			}
			
			currentPosition++;
		}
		
		if( !hasError )
		{
			input.setValue(Integer.parseInt(stack.pop().getToken()));
		}
	}
}
