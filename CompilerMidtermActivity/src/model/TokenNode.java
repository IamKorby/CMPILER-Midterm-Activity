package model;

public class TokenNode
{
	private String token;
	private TokenType tokenType;
	
	public TokenNode( String token, TokenType tokenType )
	{
		this.token = token;
		this.tokenType = tokenType;
	}
	
	public String getToken()
	{
		return token;
	}
	
	public void setToken(String token)
	{
		this.token = token;
	}
	
	public TokenType getTokenType()
	{
		return tokenType;
	}
	
	public void setTokenType(TokenType tokenType)
	{
		this.tokenType = tokenType;
	}
	
}
