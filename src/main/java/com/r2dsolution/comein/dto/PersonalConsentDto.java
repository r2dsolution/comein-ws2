package com.r2dsolution.comein.dto;

public class PersonalConsentDto {
	
    private String token;
    private String secretCode;
    
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getSecretCode() {
		return secretCode;
	}
	public void setSecretCode(String secretCode) {
		this.secretCode = secretCode;
	}
    
}
