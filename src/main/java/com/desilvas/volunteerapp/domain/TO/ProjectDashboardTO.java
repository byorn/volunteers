package com.desilvas.volunteerapp.domain.TO;

import java.sql.Date;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;



public class ProjectDashboardTO {
	
	
	Long projectID;
	
	String projectDetail;
	
	String projectTitle;
	
	Timestamp postedOn;
	
	Long numberOfVolunteers;
	
	String projectState;
		
	List<UserTO> projectVolunteers;
	
	boolean projectHasAVolunteer;
	
	

	
	
	
	public boolean isProjectHasAVolunteer() {
		return projectHasAVolunteer;
	}

	public void setProjectHasAVolunteer(boolean projectHasAVolunteer) {
		this.projectHasAVolunteer = projectHasAVolunteer;
	}

	public List<UserTO> getProjectVolunteers() {
		return projectVolunteers;
	}

	public void setProjectVolunteers(List<UserTO> projectVolunteers) {
		this.projectVolunteers = projectVolunteers;
	}

	public ProjectDashboardTO(){
		projectVolunteers = new ArrayList<UserTO>();
	}
	
	public Long getProjectID() {
		return projectID;
	}

	public void setProjectID(Long projectID) {
		this.projectID = projectID;
	}

	public String getProjectDetail() {
		return projectDetail;
	}

	public void setProjectDetail(String projectDetail) {
		this.projectDetail = projectDetail;
	}

	public Timestamp getPostedOn() {
		return postedOn;
	}

	public void setPostedOn(Timestamp postedOn) {
		this.postedOn = postedOn;
	}

	public Long getNumberOfVolunteers() {
		return numberOfVolunteers;
	}

	public void setNumberOfVolunteers(Long numberOfVolunteers) {
		this.numberOfVolunteers = numberOfVolunteers;
	}

	public String getProjectState() {
		return projectState;
	}

	public void setProjectState(String projectState) {
		this.projectState = projectState;
	}

	
	 

	public String getProjectTitle() {
		return projectTitle;
	}

	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
	}

}
