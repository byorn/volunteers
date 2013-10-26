package com.desilvas.volunteerapp.web;

import java.util.HashMap;
import java.util.Map;

public class JSONResponse {

	private boolean success;
	
	private Map<String, String> fieldMap;
	
	public JSONResponse(){
		fieldMap = new HashMap<String, String>();
	}

	public Map<String, String> getFieldMap() {
		return fieldMap;
	}
	public void setFieldMap(Map<String, String> fieldMap) {
		this.fieldMap = fieldMap;
	}
	
		
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}

	
}
