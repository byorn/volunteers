package com.desilvas.volunteerapp.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.validation.ObjectError;

import com.desilvas.volunteerapp.util.Util;
import com.desilvas.volunteerapp.util.VolyConstants;


public class ViewObject {

	
	
	private Integer projectId;
	
	private String userIsNew;
	
	private String userName;
	
	private String password;
	
	private Map<String,String> spanErrorMap;
	
	
	public ViewObject(){
		this.spanErrorMap = new HashMap<String,String>();
	}
	
	public Map<String,String> getErrors(){
		return this.spanErrorMap;
	}
	

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	
	
	public String getUserIsNew() {
		return userIsNew;
	}

	public void setUserIsNew(String userIsNew) {
		this.userIsNew = userIsNew;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	
	public boolean isExistingUser(){
		if(VolyConstants.EXISTING.toString().equals(userIsNew)){
			return true;
		}
		return false;
	}
	
	


	public boolean isValidView() {

		boolean isLoggedIn = Util.isUserLoggedIn();

		boolean isValid = true;

		if (!isLoggedIn) {

			if (userIsNew == null || "".equals(userIsNew)) {
				
						
				spanErrorMap.put("newUser","Please select if you are a new or existing user");
				isValid = false;

			}

			if ("".equals(userName.trim()) || "".equals(password.trim()) || !Util.validEmail(userName)) {
			
				spanErrorMap.put("usernameAndPassword","Please make sure if you have entered user name and password correctly");
				isValid = false;
			}
			
	

			
		}

		return isValid;
	}
	
	


}
