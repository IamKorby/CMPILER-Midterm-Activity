package controller;

import java.util.ArrayList;

import model.TokenList;
import model.TokenNode;
import model.TokenType;
import model.TokenizedInput;

public class Lexer
{
	// FUNCTION  : parses input string into tokens and checks their token type
	// PARAMETER : input string to be parsed
	// RETURN    : arraylist of tokens with tokentypes
	public static TokenizedInput parseInput(String input)
	{
		ArrayList<TokenNode> parsedInput = new ArrayList<>(0);
		String num = "";
		int currChar = 0;
		boolean hasUnknown = false;
		
		// keep on reading character by character while currChar is not in the last character of the string
		while( currChar != input.length() )
		{
			// check if character is an operator
			if( TokenList.containsOperator( input.charAt(currChar)) )
			{
				// create a new TokenNode object containing the string value of the character and the tokentype operator
				// add the tokenNode object to the parsedInput arraylist
				// increment currChar afterwards to check the next character
				parsedInput.add( new TokenNode(String.valueOf(input.charAt(currChar)), TokenType.OPERATOR) );
				currChar++;
			}
			// check if character is a grouping symbol
			else if( TokenList.containsGrouper( input.charAt(currChar)) )
			{
				// create a new TokenNode object containing the string value of the character and the tokentype grouping symbol
				// add the tokenNode object to the parsedInput arraylist
				// increment currChar afterwards to check the next character
				parsedInput.add( new TokenNode(String.valueOf(input.charAt(currChar)), TokenType.GROUPING_SYMBOL) );
				currChar++;
			}
			// check if character is an operand
			else if( TokenList.containsNumber( input.charAt(currChar)) )
			{
				// initialize num to empty string
				num = "";
				
				// continue checking and adding the next characters to num while the character is a number
				do
				{
					// concatenate the character to num
					// increment currChar afterwards to check the next character
					num += String.valueOf(input.charAt(currChar));
					currChar++;
				} while( currChar != input.length() && TokenList.containsNumber( input.charAt(currChar)) );
				
				// create a new tokenNode object containing the string value of the character / set of characters and the tokenType operand
				// add the tokenNode object to the parsedInput arraylist
				parsedInput.add( new TokenNode(num, TokenType.OPERAND) );
			}
			// other symbols
			else
			{
				// create a new TokenNode object containing the string value of the character and the tokentype unknown
				// add the tokenNode object to the parsedInput arraylist
				// increment currChar afterwards to check the next character
				parsedInput.add( new TokenNode(String.valueOf(input.charAt(currChar)), TokenType.UNKNOWN) );
				currChar++;
				
				// this input has an unknown value, it fails the lexical analysis phase
				hasUnknown = true;
			}
		}
		
		return new TokenizedInput( parsedInput, !hasUnknown);
	}
}
