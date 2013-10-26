package com.desilvas.volunteerapp.domain.TO;

import java.sql.Blob;



public class UserTO {
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	private Long userId;
	private String userName;
	public Blob getImage() {
		return image;
	}
	public void setImage(Blob image) {
		this.image = image;
	}
	private Blob image;
	
	Long projVolId;
	
	public Long getProjVolId() {
		return projVolId;
	}

	public void setProjVolId(Long projVolId) {
		this.projVolId = projVolId;
	}
	
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	String imagePath;
	
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	boolean selected;

}
