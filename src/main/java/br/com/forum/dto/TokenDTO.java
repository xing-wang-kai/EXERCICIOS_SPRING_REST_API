package br.com.forum.dto;

public class TokenDTO {
	
	private String token;
	private String Strategy;
	
	
	
	public TokenDTO(String token, String strategy) {
		this.token = token;
		this.Strategy = strategy;
	}
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getStrategy() {
		return Strategy;
	}
	public void setStrategy(String strategy) {
		Strategy = strategy;
	}

}
