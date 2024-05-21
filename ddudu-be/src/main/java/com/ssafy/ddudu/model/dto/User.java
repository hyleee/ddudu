package com.ssafy.ddudu.model.dto;

public class User {

	private String userId;
	private String userPassword;
	private String userName;
	private String userEmail;
	private int userAge;
	private int userHeight;
	private int userWeight;
	private String userArea;
	private String userProfile;
	private int userPermission;
	
	public User() {
		super();
	}
	
	public User(String userId, String userPassword, String userName, String userEmail, int userAge, int userHeight,
			int userWeight, String userArea, String userProfile, int userPermission) {
		super();
		this.userId = userId;
		this.userPassword = userPassword;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userAge = userAge;
		this.userHeight = userHeight;
		this.userWeight = userWeight;
		this.userArea = userArea;
		this.userProfile = userProfile;
		this.userPermission = userPermission;
	}

	public int getUserPermission() {
		return userPermission;
	}

	public void setUserPermission(int userPermission) {
		this.userPermission = userPermission;
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public int getUserAge() {
		return userAge;
	}
	public void setUserAge(int userAge) {
		this.userAge = userAge;
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
	public String getUserProfile() {
		return userProfile;
	}
	public void setUserProfile(String userProfile) {
		this.userProfile = userProfile;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userPassword=" + userPassword + ", userName=" + userName + ", userEmail="
				+ userEmail + ", userAge=" + userAge + ", userHeight=" + userHeight + ", userWeight=" + userWeight
				+ ", userArea=" + userArea + ", userProfile=" + userProfile + ", userPermission=" + userPermission
				+ "]";
	}
	
	


}