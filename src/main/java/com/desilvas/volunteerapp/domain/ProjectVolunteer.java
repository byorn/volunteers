package com.desilvas.volunteerapp.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "project_volunteers")
public class ProjectVolunteer implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "proj_vol_id")
    @GeneratedValue
    private Integer projectVolunteersId;
    
    @JoinColumn(name = "project_id", referencedColumnName = "project_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Project project;
   
     

    @JoinColumn(name = "user_id", referencedColumnName = "USER_ID")
    @ManyToOne(fetch = FetchType.EAGER)
    private User postedBy;
    
    
    @Column(name = "selected")
    private Boolean selected;
    
  
	
    public Boolean getSelected() {
		return selected;
	}



	public void setSelected(Boolean selected) {
		this.selected = selected;
	}



	public ProjectVolunteer() {

    
    }

    
    
    public Integer getProjectVolunteersId() {
		return projectVolunteersId;
	}

	public void setProjectVolunteersId(Integer projectVolunteersId) {
		this.projectVolunteersId = projectVolunteersId;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	
	public User getPostedBy() {
		return postedBy;
	}

	public void setPostedBy(User postedBy) {
		this.postedBy = postedBy;
	}

	


    
   
    
}
