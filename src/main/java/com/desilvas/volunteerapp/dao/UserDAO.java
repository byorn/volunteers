package com.desilvas.volunteerapp.dao;

import java.util.List;

import com.desilvas.volunteerapp.domain.User;
import com.desilvas.volunteerapp.domain.UserFeedBack;
import com.desilvas.volunteerapp.domain.UserImage;
import com.desilvas.volunteerapp.domain.UserSkills;


public interface UserDAO {
	
	public void saveUser(User user);
	
	public User getUserForUserName(String userName);
	
	
	public void uploadImage(UserImage ui);
	
	
	public UserImage getUserImage(Integer userId);
	
	public void updateUser(User user);
	
	public List<UserFeedBack> getUserFeedback(Integer userId);
	
	
	public void saveUserSkills(UserSkills us);
	
	public UserSkills getUserSkills(Integer userId);
	
	public void createUserFeedBack(UserFeedBack ufb);
	
	
}
