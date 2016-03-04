package driver;

import java.util.ArrayList;

import controller.FileReader;
import controller.Lexer;
import controller.Checker;
import model.TokenNode;

public class Main
{
	public static void main(String[] args)
	{
		FileReader filereader = new FileReader();
		Checker regexCheck = new Checker();
		
		ArrayList<String> input = filereader.read("src/input.txt");
		
		for ( String s : input )
		{
			System.out.println(s);
		}
		
		System.out.println();
		
		Lexer lexer = new Lexer();
		
		ArrayList<TokenNode> parsedInput = lexer.parseInput(input.get(4));
		
		for ( TokenNode n : parsedInput )
		{
			System.out.println(n.getToken() + "\t" + n.getTokenType());
		}
		
		System.out.println("\nRegex & Parenthesis Checker:");
		
		for ( String s : input )
		{
			if(regexCheck.regexChecker(s))
			{
				System.out.println(s + " - MATCHED");
			}
			else
			{
				System.out.println(s + " - NOT MATCHED");
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