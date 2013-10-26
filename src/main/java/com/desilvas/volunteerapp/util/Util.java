package com.desilvas.volunteerapp.util;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.security.core.context.SecurityContextHolder;

import com.desilvas.volunteerapp.util.VolyConstants.PROJECT_STATES;

public class Util {

	
	public static boolean isUserLoggedIn(){
		
		return !"anonymousUser".equals(SecurityContextHolder.getContext().getAuthentication().getName());
		
	}
	
	public static String toHTMLList(List<String> data){
		StringBuilder sb = new StringBuilder();
		for(String string:data){
			sb.append(string+"<br/>");
		}
		return sb.toString();
	}
	
	

	public static boolean validEmail(String aEmailAddress) {
		 final String EMAIL_PATTERN = 
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		 
		 return Pattern.compile(EMAIL_PATTERN).matcher(aEmailAddress).matches();
	}
	
	
	
	
	public static String forProjectCode(String code){
		if(code==null){
			return "";
		}else if(code.equals(PROJECT_STATES.ASN.toString())){
	        return "Assigned";
		}else if(code.equals(PROJECT_STATES.NEW.toString())){
			return "New/Open";
		}else if(code.equals(PROJECT_STATES.RPN.toString())){
			return "Reopened";
		}else if(code.equals(PROJECT_STATES.CLS.toString())){
			return "Closed";
		}else if(code.equals(PROJECT_STATES.COM.toString())){
			return "Complete";
		}
		return "Not found";
	}
	
	
	
	
	

	
	
	
}
