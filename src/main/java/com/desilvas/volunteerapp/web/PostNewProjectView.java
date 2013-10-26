package com.desilvas.volunteerapp.web;

import javax.validation.constraints.Size;

import com.desilvas.volunteerapp.util.VolyConstants;

public class PostNewProjectView {

	@Size(min=5, max=30, message="Project Title should have atleast 5 characters and not more than 30")
	private String projectTitle;
	
	@Size(min=20, max=500, message="Project Details should have minimum of 20 characters and not more than 500")
	private String projectDetail;
	
	@Size(min=3,max=3, message="Oops! Did you tamper with the category code?")
	private String categoryCode;
	
	
	
	
	public String getProjectTitle() {
		return projectTitle;
	}

	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
	}

	public String getProjectDetail() {
		return projectDetail;
	}

	public void setProjectDetail(String projectDetail) {
		this.projectDetail = projectDetail;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	
	
	
}
