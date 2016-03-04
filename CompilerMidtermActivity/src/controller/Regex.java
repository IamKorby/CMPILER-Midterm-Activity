package controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
	
	public boolean regexChecker(String input)
	{
		String pattern = "(\\()*[\\+\\-]?(\\d+)(([\\*\\-\\+\\/\\%](\\()*([\\+\\-]?)\\d+)*(\\))*)*";
		
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(input);
		
		return m.matches();
	}
	
	/*public static void main( String args[] )
	{

	      // String to be scanned to find the pattern.
	      String line = "11+20-3*4%5";
	      String line2 = "(3)(5)";
	      //String pattern = "(\\d+)([\\*\\-\\+\\/\\%][\\+\\-]?\\d+)*";
	      String pattern2 = "(\\()*[\\+\\-]?(\\d+)(([\\*\\-\\+\\/\\%](\\()*([\\+\\-]?)\\d+)*(\\))*)*";

	      // Create a Pattern object
	      Pattern r = Pattern.compile(pattern2);

	      // Now create matcher object.
	      Matcher m = r.matcher(line2);
	      
	      if( m.matches() )// if true aka line matches the pattern
	      {
	    	  // Prints the input (line); group(0) always prints
	    	  System.out.println("MATCHED");
	    	  System.out.println(m.group(0)); 
	      }
	      else
	      {
	    	  System.out.println("Input did not match the pattern");
	      }
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
