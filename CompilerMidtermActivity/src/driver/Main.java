package driver;

import java.util.ArrayList;

import controller.FileReader;
import controller.Lexer;
import controller.Checker;
import controller.Evaluator;
import controller.PrecedenceParser;
import model.ErrorType;
import model.TokenNode;
import model.TokenizedInput;

public class Main
{
	public static void main(String[] args)
	{
		ArrayList<TokenizedInput> tokenizedInputs = new ArrayList<TokenizedInput>(0);
		ArrayList<String> input = FileReader.read("input.txt");
		
		// print original inputs
		for ( String s : input )
		{
			System.out.println(s);
		}
		
		System.out.println();
		
		// parse inputs into tokens
		for( String s: input )
		{
			tokenizedInputs.add(Lexer.parseInput(s));
		}
		
		// print tokenizedInputs
		System.out.println("\nLexical Checker:");
		for( TokenizedInput i : tokenizedInputs )
		{
			for ( TokenNode n : i.getInfixTokens() )
			{
				System.out.println(n.getToken() + "\t" + n.getTokenType());
			}
			System.out.println();
		}
		
		// print lexical analysis results
//		for( int i = 0; i < tokenizedInputs.size(); i++ )
//		{
//			if( tokenizedInputs.get(i).getErrorType() == ErrorType.LEXICAL_ERROR )
//			{
//				System.out.println(input.get(i) + " - " + tokenizedInputs.get(i).getErrorType().toString());
//			}
//			else 
//			{
//				System.out.println(input.get(i) + " - ACCEPTED");
//			}
//		}
		
		// check if tokenizedInput passed lexical analysis, if yes proceed, else skip
		// check tokenizedInputs' syntax
		// print result
		System.out.println("\nRegex & Parenthesis Checker:");
		for( TokenizedInput t : tokenizedInputs )
		{
			// check if tokenizedInput passed the lexical analysis, if yes check syntax
			if( t.getErrorType() != ErrorType.LEXICAL_ERROR )
			{
				Checker.checkSyntax(t);
				
				if( t.getErrorType() == ErrorType.NONE )
				{
					System.out.println(t.getOriginalInput() + " - ACCEPTED");
				}
				else
				{
					System.out.println(t.getOriginalInput() + " - " + t.getErrorType().toString());
				}
			}
			else
			{
				System.out.println(t.getOriginalInput() + " - " + t.getErrorType().toString() + " -> Not Checked for Syntax");
			}
		}
                
		// convert infix tokens to postfix tokens
          System.out.println("\nConvert to postfix");
          for( TokenizedInput t : tokenizedInputs )
          {
          	if( t.getErrorType() == ErrorType.NONE )
          	{
	          	t.setPostfixTokens(PrecedenceParser.Convert2PostFix(t.getInfixTokens()));
	          	
	          	for (int j = 0; j < t.getPostfixTokens().size(); j++)
	     		{
	     			System.out.print(t.getPostfixTokens().get(j).getToken() + " ");
	     		}
	     		System.out.println("");
          	}
          	else
          	{
          		System.out.println(t.getErrorType().toString());
          	}
          }
          
          // check if tokenizedInput has passed syntax analysis
          // evaluate expression
          System.out.println("\nEvaluate Values");
          for( TokenizedInput t : tokenizedInputs )
          {
          	if( t.getErrorType() == ErrorType.NONE )
          	{
          		Evaluator.evaluate(t);
          	}
          }
          
          // check if tokenizedInput has passed lexical analysis, if not display LEXICAL ERROR
          // check if tokenizedInput has passed syntax analysis, if not display SYNTAX ERROR
          // else print evaluated values
          System.out.println("\nDisplay Values");
          for( TokenizedInput t : tokenizedInputs )
          {
          	if( t.getErrorType() != ErrorType.NONE )
          	{
          		System.out.println(t.getErrorType().toString());
          	}
          	else
          	{
          		System.out.println(t.getValue());
          	}
          }
          
          System.out.println("-- END OF PROGRAM --");
//          System.out.println("MIN: " + Integer.MIN_VALUE);
//          System.out.println("MAX: " + Integer.MAX_VALUE);
          
//		System.out.println("TRIAL");
//		System.out.println(TokenList.containsNumber(Integer.toString(3)));
//		System.out.println(TokenList.containsSymbol('-'));
//		
//		String x = "+";
//		
//		System.out.println(TokenList.containsSymbol(x));
//		System.out.println(-2*-3);
	}
}


// PRECEDENCE
// ()
// * / %
// + -