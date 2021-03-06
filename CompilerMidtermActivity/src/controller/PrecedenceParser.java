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

/**
 *
 * @author WilliamPC
 */
public class PrecedenceParser
{
	public static void ConvertToPostFix(TokenizedInput input)
	{
		if ( input.getErrorType() == ErrorType.NONE )
		{
			ArrayList<TokenNode> inFix = input.getInfixTokens();
			ArrayList<TokenNode> postFix = new ArrayList<>();
			Stack<TokenNode> stack = new Stack<>();
			TokenNode currentToken;
			TokenNode stackTop;
			int ISP; // In stack priority
			int ICP; // Incoming priority
	
			for (TokenNode list1 : inFix)
			{
				currentToken = list1;
				if (currentToken.getTokenType() == TokenType.OPERAND)
				{
					postFix.add(currentToken);
				}
				else if (currentToken.getTokenType() == TokenType.GROUPING_SYMBOL)
				{
					String token = currentToken.getToken();
					if ("(".equals(token))
					{
						stack.push(currentToken);
					}
					else if (")".equals(token))
					{
						TokenNode stackToken = stack.pop();
						if ( stackToken.getTokenType() == TokenType.OPERATOR ||
						     stackToken.getTokenType() == TokenType.OPERATOR_UNARY )
						{
							postFix.add(stackToken);
						}
					}
				}
				else if ( currentToken.getTokenType() == TokenType.OPERATOR ||
						currentToken.getTokenType() == TokenType.OPERATOR_UNARY )
				{
					ICP = assignICP(currentToken);
					if (stack.empty())
					{
						stack.push(currentToken);
					}
					else
					{
						TokenNode top = stack.peek();
						ISP = assignISP(top);
						if (ISP < ICP)
						{
							stack.push(currentToken);
						}
						else if (ISP >= ICP)
						{
							while (ISP >= ICP && !stack.empty())
							{
	                                                    postFix.add(stack.pop());
								if (!stack.empty())
								{
									top = stack.peek();
									ISP = assignISP(top);
								}
								
							}
							stack.push(currentToken);
						}
					}
				}
			}
			if (!stack.empty())
			{
				while (!stack.empty())
				{
					stackTop = stack.peek();
					if ("(".equals(stackTop.getToken()))
					{
						stack.pop();
					}
					
					if( !stack.empty() )
					{
						postFix.add(stack.pop());
					}
				}
			}
	
			input.setPostfixTokens(postFix);
		}
	}

	static int assignICP(TokenNode token)
	{
		int ICP = 0;
		String tokenSymbol = token.getToken();
		if ("*".equals(tokenSymbol) || "/".equals(tokenSymbol) || "%".equals(tokenSymbol))
		{
			ICP = 2;
		}
		else if ("+".equals(tokenSymbol) || "-".equals(tokenSymbol))
		{
			ICP = 1;
		}
		else if ("(".equals(tokenSymbol))
		{
			ICP = 4;
		}
		return ICP;

	}

	static int assignISP(TokenNode token)
	{
		int ISP = 0;
		String tokenSymbol = token.getToken();
		if ("*".equals(tokenSymbol) || "/".equals(tokenSymbol) || "%".equals(tokenSymbol))
		{
			ISP = 2;
		}
		else if ("+".equals(tokenSymbol) || "-".equals(tokenSymbol))
		{
			ISP = 1;
		}
		else if ("(".equals(tokenSymbol))
		{
			ISP = 0;
		}
		return ISP;
	}
}
