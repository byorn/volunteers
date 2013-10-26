package com.desilvas.volunteerapp.service;

import java.util.ArrayList;
import java.util.List;

public class BusinessResponse {


	private List<String> errorMessages;
	private List<String> successMessages;
	
	public BusinessResponse(){
		errorMessages = new ArrayList<String>();
		successMessages = new ArrayList<String>();
	}
	
	public static BusinessResponse success(){
		BusinessResponse br = new BusinessResponse();
		br.addSuccessMessage("Success");
		return br;
	}
	

	public void addErrorMessage(String errorMessage){
		this.errorMessages.add(errorMessage);
	}
	
	public void addSuccessMessage(String successMessage){
		this.successMessages.add(successMessage);
	}
	
	public List<String> getErrorMessages(){
		return this.errorMessages;
	}
	
	public List<String> getSuccessMessages(){
		return this.successMessages;
	}
	
	public boolean isSuccess(){
		if(errorMessages.isEmpty() && !successMessages.isEmpty()){
			return true;
		}
		return false;
	}
	
	
}
