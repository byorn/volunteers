package com.desilvas.volunteerapp.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "projects")
public class Project implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "project_id")
    @GeneratedValue
    private Integer projectId;
    @Basic(optional = false)
    @Column(name = "project_title")
    private String projectTitle;
    @Basic(optional = false)
    @Column(name = "project_detail")
    private String projectDetail;
    @Column(name = "posted_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date postedDate;
    @JoinColumn(name = "posted_by", referencedColumnName = "USER_ID")
    @ManyToOne(fetch = FetchType.EAGER)
    private User postedBy;
    
    @Basic(optional = true)
    @JoinColumn(name = "category_code", referencedColumnName = "category_code")
    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;
    
    @JoinColumn(name = "project_id", referencedColumnName = "project_id")
    @OneToMany(fetch = FetchType.EAGER)
    private List<ProjectVolunteer> projectVolunteers;
    

	public List<ProjectVolunteer> getProjectVolunteers() {
		return projectVolunteers;
	}

	public void setProjectVolunteers(List<ProjectVolunteer> projectVolunteers) {
		this.projectVolunteers = projectVolunteers;
	}

	@Column(name = "status")
    private String status;
    
    public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


   
	public Project() {
    }

    public Project(Integer projectId) {
        this.projectId = projectId;
    }

    public Project(Integer projectId, String projectTitle, String projectDetail) {
        this.projectId = projectId;
        this.projectTitle = projectTitle;
        this.projectDetail = projectDetail;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

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

    public Date getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(Date postedDate) {
        this.postedDate = postedDate;
    }

    public User getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(User postedBy) {
        this.postedBy = postedBy;
    }
    public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

   
    
}
