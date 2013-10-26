package com.desilvas.volunteerapp.web;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.desilvas.volunteerapp.util.VolyConstants;

public class VolunteerForProjectView {

	@NotNull
	@Size(min=10)
	private String messageText;
	
	@NotNull
	private Integer projectId;

	
	
	
	
	
   public String getMessageText() {
		return messageText;
	}

	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	

	
	


		
		
}
