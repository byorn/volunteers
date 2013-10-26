package com.desilvas.volunteerapp.dao;

import java.util.List;

import com.desilvas.volunteerapp.domain.Message;
import com.desilvas.volunteerapp.domain.TO.UserTO;


public interface MessageDAO {
	
	public void createMessage(Message message);
	
	
	public List<Message> getMessagesFor(Integer projectVolId);
	
	
	
	
}
