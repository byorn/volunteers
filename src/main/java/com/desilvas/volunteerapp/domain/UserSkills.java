package com.desilvas.volunteerapp.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 *
 * @author byorn
 */
@Entity
@Table(name = "user_skills")
public class UserSkills implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "user_id")
    private Integer userId;
    
    
    public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	@Column(name = "skills")
    private String skills;
	
	@Column(name = "volunteer_reason")
    private String volunteerReason;


	public String getVolunteerReason() {
		return volunteerReason;
	}


	public void setVolunteerReason(String volunteerReason) {
		this.volunteerReason = volunteerReason;
	}


	public String getSkills() {
		return skills;
	}


	public void setSkills(String skills) {
		this.skills = skills;
	}
       
}
