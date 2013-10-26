package com.desilvas.volunteerapp.domain;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "images")
public class UserImage {

	@Id
	@Column(name="user_id")
	private Integer userId;
	
	
	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	public Blob getImage() {
		return image;
	}


	public void setImage(Blob image) {
		this.image = image;
	}


	@Column(name="image")
	private Blob image;
	
	@Column(name="uploaded_image")
	private Blob uploadedImage;


	public Blob getUploadedImage() {
		return uploadedImage;
	}


	public void setUploadedImage(Blob uploadedImage) {
		this.uploadedImage = uploadedImage;
	}
	
}
