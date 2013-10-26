package com.desilvas.volunteerapp.dao;

import java.util.List;
import java.util.Map;

import com.desilvas.volunteerapp.domain.Project;
import com.desilvas.volunteerapp.domain.ProjectVolunteer;


public interface ProjectVolunteersDAO {
	
	public Integer projectVolunteer(Integer projectId, Integer userId) ;

	
	public Map<Project,List<ProjectVolunteer>> getVolunteerNamesForDashboard(Integer projectPosterUserId);
	
	public Integer  getProjectVolunteerId(Integer projectId, Integer userId);
	
	public ProjectVolunteer getProjectVolunteer(Integer projectVolunteerId);
	
	public void updateProjectVolunteer(ProjectVolunteer projectVolunteer);
	
	public void unselectAllVolunteersForProject(Integer projectId);
	
	 public List<ProjectVolunteer> getProjectsVolunteeredBy(Integer userId);
	
}
