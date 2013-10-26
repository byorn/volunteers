package com.desilvas.volunteerapp.domain;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQuery;


/**
 *
 * @author byorn
 */
@Entity
@Table(name = "messages")
public class Message implements Serializable {
    private static final long serialVersionUID = 1L;
    
    
    
	@Id
    @GeneratedValue
    @Column(name = "message_id")
    private Integer messageId;
    
    
    @Column(name = "message")
    private String message;
    
    @Column(name = "proj_vol_id")
    private Integer projectVolunteerId;
    
    @JoinColumn(name = "created_by", referencedColumnName = "user_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private User createdBy;
    
    
    
    
    
    public Integer getMessageId() {
		return messageId;
	}

	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getProjectVolunteerId() {
		return projectVolunteerId;
	}

	public void setProjectVolunteerId(Integer projectVolunteerId) {
		this.projectVolunteerId = projectVolunteerId;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}


 
    
}
