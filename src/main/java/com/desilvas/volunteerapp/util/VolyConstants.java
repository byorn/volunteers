package com.desilvas.volunteerapp.util;

public enum VolyConstants {

	NEW, EXISTING;
	
	
	public enum JSP_FIELDS{
		newUser,usernameAndPassword,errorResponse,successResponse
		,volunteersForProjectList
		;
	}
	
	public enum JSP_COLLECTION_FIELDS{
		volunteerNames, messages, projectsVolunteered;
	}
	
	
	public enum PROJECT_STATES{
		ASN, 
		NEW, 
		RPN,  
		CLS,  
		COM ; 
		
		
	}
	
	
	
}
