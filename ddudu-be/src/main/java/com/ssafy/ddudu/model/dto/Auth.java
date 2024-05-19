package com.ssafy.ddudu.model.dto;

public class Auth {
	private String userId;
	private String refreshToken;
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	
	@Override
	public String toString() {
		return "AuthDto [userId=" + userId + ", refreshToken=" + refreshToken + "]";
	}
	
	
	
	

}
