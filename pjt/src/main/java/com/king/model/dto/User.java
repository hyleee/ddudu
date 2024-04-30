package com.king.model.dto;

public class User {

	private String userId;
	private String password;
	private String email;
	private int userHeight;
	private int userWeight;
	private String userArea;
	private byte[] profile;

	public User(String userId, String password, String email, int userHeight, int userWeight, String userArea,
			byte[] profile) {
		super();
		this.userId = userId;
		this.password = password;
		this.email = email;
		this.userHeight = userHeight;
		this.userWeight = userWeight;
		this.userArea = userArea;
		this.profile = profile;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getUserHeight() {
		return userHeight;
	}

	public void setUserHeight(int userHeight) {
		this.userHeight = userHeight;
	}

	public int getUserWeight() {
		return userWeight;
	}

	public void setUserWeight(int userWeight) {
		this.userWeight = userWeight;
	}

	public String getUserArea() {
		return userArea;
	}

	public void setUserArea(String userArea) {
		this.userArea = userArea;
	}

	public byte[] getProfile() {
		return profile;
	}

	public void setProfile(byte[] profile) {
		this.profile = profile;
	}

}
