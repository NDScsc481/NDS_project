package com.nds.pkg;

public class LoginInfo {
	private String username;
	private String password;
	
	LoginInfo(){
		username = "default";
		password = "default";
	}
	LoginInfo(String user, String pass){
		username = user;
		password = pass;
	}
	
	public void setUser(String name){
		username = name;
	}
	
	public void setPass(String pass){
		password = pass;
	}
	
	public String getUser(){
		return username;
	}
	
	public String getPass(){
		return password;
	}
}
