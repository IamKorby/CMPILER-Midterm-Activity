package controller;

import java.util.EmptyStackException;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Checker {
	
	// Count if the num of '(' == num of ')' using stacks
	public static boolean parenthesisChecker(String input)
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
	
	public static boolean regexChecker(String input)
	{
		String pattern = "(\\()*[\\+\\-]?(\\d+)(([\\*\\-\\+\\/\\%](\\()*([\\+\\-]?)\\d+)*(\\))*)*";
		
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(input);
		
		if(parenthesisChecker(input) && m.matches())
		{
			return true;
		}
		
		return false;
	}
	
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
	 */

	
	/*
	 * INPUT:	1+2*3
	 * PATTERN:	(\\d+)([\\*\\-\\+\\/\\%][\\+\\-]?\\d+)*
	 * 	
	 * MAIN PATTERN:	(\()*[+-]?(\d+)(([*\-+/%](\()*([+-]?)\d+)*(\))*)*
	 * 
	 */
}
