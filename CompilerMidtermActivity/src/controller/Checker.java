package controller;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.ErrorType;
import model.TokenNode;
import model.TokenizedInput;

public class Checker 
{
	public static void checkSyntax( TokenizedInput input )
	{
		// check if tokenizedInput passed the lexical analysis, if yes check syntax
		if( input.getErrorType() != ErrorType.LEXICAL_ERROR )
		{
			// Using original inputs: where you use parenthesisChecker by passing input.getOriginalInput()
			// Using tokenizedInputs: where you use regexChecker by passing input.getInfixTokens()
			if( parenthesisChecker(input.getOriginalInput()) && regexChecker(input.getInfixTokens()) )
			{
				input.setPassedChecker(true);
			}
			else
			{
				input.setPassedChecker(false);
			}
		}
	}
	
	// Count if the num of '(' == num of ')' using stacks
	private static boolean parenthesisChecker(String input)
	{
		Stack<Character> stack = new Stack<Character>();
		try
		{
			for(int i = 0; i < input.length(); i++)
			{
				char c = input.charAt(i);
				
				if(c == '(')
				{
					stack.push(input.charAt(i));
				}
				else if(c == ')')
				{
					stack.pop();
				}
			}
			
			if(stack.empty())
			{
				return true;
			}
		}
		catch(EmptyStackException e)
		{
			//e.printStackTrace();
		}
		
		return false;
	}
	
	// Checks if the input Token Type conforms to the syntax
	private static boolean regexChecker(ArrayList<TokenNode> inputTokenNode)
	{
		//OLD REGEX aka NO OPERATOR_UNARY
//		String pattern = "(GROUPING_SYMBOL)*(OPERAND)(((OPERATOR)(GROUPING_SYMBOL)*(OPERAND))*(GROUPING_SYMBOL)*)*";
		//NEW REGEX aka with OPERATOR_UNARY
//		String pattern = "(GROUPING_SYMBOL)*(((OPERATOR_UNARY|OPERATOR)(GROUPING_SYMBOL)*)*(OPERAND))+(GROUPING_SYMBOL)*";
//		String pattern = "(GROUPING_SYMBOL)*(OPERAND)(((OPERATOR_UNARY|OPERATOR)(GROUPING_SYMBOL)*(OPERAND))*(GROUPING_SYMBOL)*)*";
//		String pattern = "(OPERATOR_UNARY)?(GROUPING_SYMBOL)*(OPERAND)(((OPERATOR_UNARY|OPERATOR)(GROUPING_SYMBOL)*(OPERAND))*(GROUPING_SYMBOL)*)*";
		String pattern = "(GROUPING_SYMBOL)*(OPERATOR_UNARY)?(GROUPING_SYMBOL)*(OPERAND)(((OPERATOR_UNARY|OPERATOR)(GROUPING_SYMBOL)*(OPERAND))*(GROUPING_SYMBOL)*)*";
		String inputTokenType = "";
		
		for(int i = 0; i < inputTokenNode.size(); i++)
		{
			// concat the inputTokenNode.get(i).getTokenType.toString();
			// we use this for the CFG-Regex part of the syntax analysis
			inputTokenType += inputTokenNode.get(i).getTokenType().toString();
		}
		
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(inputTokenType);
		
		if(m.matches())
		{
			return true;
		}
		
		return false;
	}
	
	/*public static boolean parenthesisChecker(String input)
	{
		Stack<Character> stack = new Stack<Character>();
		try
		{
			for(int i = 0; i < input.length(); i++)
			{
				char c = input.charAt(i);
				
				if(c == '(')
				{
					stack.push(input.charAt(i));
				}
				else if(c == ')')
				{
					stack.pop();
				}
			}
			
			if(stack.empty())
			{
				return true;
			}
		}
		catch(EmptyStackException e)
		{
			//e.printStackTrace();
		}
		
		return false;
	}*/
	
	/*public static boolean regexChecker(String input)
	{
		String pattern = "(\\()*[\\+\\-]?(\\d+)(([\\*\\-\\+\\/\\%](\\()*([\\+\\-]?)\\d+)*(\\))*)*";
		
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(input);
		
		if(parenthesisChecker(input) && m.matches())
		{
			return true;
		}
		
		return false;
	}*/
	
	/*
	public static void main( String args[] )
	{
		String input = "(((1+(2*3))))))";
		
		System.out.println(parenthesisChecker(input));
	}*/
	
	
	
	/*
	 * CONSIDER THE FF:
	 * ++, -+, *+, /+, %+ -> POSITIVE
	 * +-, --, /-, *-, %- -> NEGATIVE
	 * 
	 * INPUT:	1+2*3
	 * PATTERN:	(\\d+)([\\*\\-\\+\\/\\%][\\+\\-]?\\d+)*
	 * 	
	 * MAIN PATTERN:	(\()*[+-]?(\d+)(([*\-+/%](\()*([+-]?)\d+)*(\))*)*
	 * CFG PATTERN:		(GROUPING_SYMBOL)*(OPERAND)(((OPERATOR)(GROUPING_SYMBOL)*(OPERAND))*(GROUPING_SYMBOL)*)*
	 * 
	 */
}
