package model;

import java.util.ArrayList;

public class TokenizedInput
{
	private String originalInput;
	private ArrayList<TokenNode> infixTokens;
	private ArrayList<TokenNode> postfixTokens;
	private boolean hasPassedLexer;
	private boolean hasPassedChecker;
	private ErrorType errorType;
	private int value;
	
	public TokenizedInput( String originalInput, ArrayList<TokenNode> infixTokens, boolean hasPassedLexer )
	{
		this.originalInput = originalInput;
		this.infixTokens = infixTokens;
		this.hasPassedLexer = hasPassedLexer;
		
		// if TokenizedInput failed in lexical analysis, it also automatically fails syntax analysis
		if( !this.hasPassedLexer )
		{
			this.errorType = ErrorType.LEXICAL_ERROR;
			hasPassedChecker = false;
		}
	}
	
	public String getOriginalInput()
	{
		return originalInput;
	}
	
	public ArrayList<TokenNode> getInfixTokens()
	{
		return infixTokens;
	}
	
	public ArrayList<TokenNode> getPostfixTokens()
	{
		return postfixTokens;
	}
	
	public void setPostfixTokens( ArrayList<TokenNode> postfixTokens )
	{
		this.postfixTokens = postfixTokens;
	}
	
	public void setPassedChecker( boolean passed )
	{
		this.hasPassedChecker = passed;
		
		if( hasPassedChecker )
		{
			this.errorType = ErrorType.NONE;
		}
		else
		{
			this.errorType = ErrorType.SYNTAX_ERROR;
		}
	}
	
	public ErrorType getErrorType()
	{
		return errorType;
	}
	
	public void setErrorType( ErrorType errorType )
	{
		this.errorType = errorType;
	}
	
	public int getValue()
	{
		return value;
	}
	
	public void setValue( int value )
	{
		this.value = value;
	}
}
