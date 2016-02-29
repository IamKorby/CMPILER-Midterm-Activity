package driver;

import java.util.ArrayList;

import controller.FileReader;
import controller.Lexer;
import model.TokenList;
import model.TokenNode;

public class Main
{
	public static void main(String[] args)
	{
		FileReader filereader = new FileReader();
		
		ArrayList<String> input = filereader.read("src/input.txt");
		
		for ( String s : input )
		{
			System.out.println(s);
		}
		
		System.out.println();
		
		Lexer lexer = new Lexer();
		
		ArrayList<TokenNode> parsedInput = lexer.parseInput(input.get(4));
		
		for ( TokenNode t : parsedInput )
		{
			System.out.println(t.getToken() + "\t" + t.getTokenType());
		}
		
//		System.out.println("TRIAL");
//		System.out.println(TokenList.containsNumber(Integer.toString(3)));
//		System.out.println(TokenList.containsSymbol('-'));
		
//		String x = "+";
//		
//		System.out.println(TokenList.containsSymbol(x));
//		System.out.println(-2*+3);
	}
}


// PRECEDENCE
// ()
// * / %
// + -