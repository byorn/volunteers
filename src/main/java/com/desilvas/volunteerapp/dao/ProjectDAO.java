package com.desilvas.volunteerapp.dao;

import java.util.List;

import com.desilvas.volunteerapp.domain.Project;
import com.desilvas.volunteerapp.domain.User;


public interface ProjectDAO {
	
	
	public void saveProject(Project project);
	public List<Project> getProjects();
	public Project getProject(Integer projectId);
	public List<Project> getProjects(int from, int pageSize);
	public Long getCountOfProjects() ;
	public void updateProjectStatus(String status, Integer projectId);
	public List<Project> getProjectsPosted(User user);

	
}
