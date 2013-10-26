package com.desilvas.volunteerapp.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 *
 * @author byorn
 */
@Entity
@Table(name = "user_feedback")
public class UserFeedBack implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "user_feedback_id")
    private Integer userFeedbackId;
    

	@JoinColumn(name = "user_id", referencedColumnName = "USER_ID")
    @ManyToOne(fetch = FetchType.EAGER)
    private User userId;
    
    @Column(name = "rating")
    private Integer rating;
    
    @Column(name = "feedback")
    private String  feedback;
    
    
    public Integer getUserFeedbackId() {
		return userFeedbackId;
	}


	public void setUserFeedbackId(Integer userFeedbackId) {
		this.userFeedbackId = userFeedbackId;
	}


	public User getUserId() {
		return userId;
	}


	public void setUserId(User userId) {
		this.userId = userId;
	}


	public Integer getRating() {
		return rating;
	}


	public void setRating(Integer rating) {
		this.rating = rating;
	}


	public String getFeedback() {
		return feedback;
	}


	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}


	public Project getProject() {
		return project;
	}


	public void setProject(Project project) {
		this.project = project;
	}


	@JoinColumn(name = "project_id", referencedColumnName = "project_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Project project;
    
    


    
}
