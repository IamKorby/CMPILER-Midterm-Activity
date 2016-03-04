package controller;

import java.util.ArrayList;
import java.util.Stack;

import model.TokenNode;
import model.TokenType;

public class Evaluator
{
	public static int evaluate(ArrayList<TokenNode> input)
	{
		Stack<TokenNode> stack = new Stack<TokenNode>();
		int currentPosition = 0;
		
		//3 5 + 2 * 6 3 - +
		
		while( currentPosition < input.size() )
		{
			if( input.get(currentPosition).getTokenType() == TokenType.OPERAND )
			{
				stack.push(input.get(currentPosition));
			}
			else if( input.get(currentPosition).getTokenType() == TokenType.OPERATOR )
			{
				int x = Integer.parseInt(stack.pop().getToken());
				int y = Integer.parseInt(stack.pop().getToken());
				
				if( input.get(currentPosition).getToken() == "+" )
				{
					y += x;
				}
				else if( input.get(currentPosition).getToken() == "-" )
				{
					y -= x;
				}
				else if( input.get(currentPosition).getToken() == "*" )
				{
					y *= x;
				}
				else if( input.get(currentPosition).getToken() == "/" )
				{
					y /= x;
				}
				else if( input.get(currentPosition).getToken() == "%" )
				{
					y %= x;
				}
				
				stack.push(new TokenNode(Integer.toString(y), TokenType.OPERAND));
			}
			
			currentPosition++;
		}
		
		return Integer.parseInt(stack.pop().getToken());
	}
}
