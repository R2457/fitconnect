package com.stackroute.model;

public class UserLoginData {

	private String userEmail;
	private String userPasswordHash;
	
	public UserLoginData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserLoginData(String userEmail, String userPasswordHash) {
		super();
		this.userEmail = userEmail;
		this.userPasswordHash = userPasswordHash;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPasswordHash() {
		return userPasswordHash;
	}
	public void setUserPasswordHash(String userPasswordHash) {
		this.userPasswordHash = userPasswordHash;
	}
	@Override
	public String toString() {
		return "UserLoginData [userEmail=" + userEmail + ", userPasswordHash=" + userPasswordHash + "]";
	}
	
}
