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
		ArrayList<String> input = FileReader.read("src/input.txt");
		
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
		
		// print tokenizedInputs[0]
		for ( TokenNode n : tokenizedInputs.get(0).getInfixTokens() )
		{
			System.out.println(n.getToken() + "\t" + n.getTokenType());
		}
		
		// TODO: check if tokenizedInput passed lexical analysis, if yes proceed, else skip
		// check tokenizedInputs' syntax 
		// TODO: set the hasPassedSyntaxAnalysis value of tokenizedInput
		// print result
		System.out.println("\nRegex & Parenthesis Checker:");
		
		for ( String s : input )
		{
			if(Checker.regexChecker(s))
			{
				System.out.println(s + " - MATCHED");
			}
			else
			{
				System.out.println(s + " - NOT MATCHED");
			}
		}
                
		// convert infix tokens to postfix tokens
		// TODO: Debug, has EmptyStackException Error
          System.out.println("\nConvert to postfix");
          for( TokenizedInput t : tokenizedInputs )
          {
          	t.setPostfixTokens(PrecedenceParser.Convert2PostFix(t.getInfixTokens()));
          }
          
          // check if tokenizedInput has passed syntax analysis
          // evaluate expression
          for( TokenizedInput t : tokenizedInputs )
          {
          	if( t.getErrorType() == ErrorType.NONE )
          	{
          		t.setValue(Evaluator.evaluate(t.getPostfixTokens()));
          	}
          }
          
          // check if tokenizedInput has passed lexical analysis, if not display LEXICAL ERROR
          // check if tokenizedInput has passed syntax analysis, if not display SYNTAX ERROR
          // else print evaluated values
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
          
//		System.out.println("TRIAL");
//		System.out.println(TokenList.containsNumber(Integer.toString(3)));
//		System.out.println(TokenList.containsSymbol('-'));
		
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