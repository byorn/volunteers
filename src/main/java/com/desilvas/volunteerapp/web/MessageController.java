package com.desilvas.volunteerapp.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.desilvas.volunteerapp.domain.Message;
import com.desilvas.volunteerapp.service.BusinessFacade;


/**
 * Handles requests for the application home page.
 */
@Controller
public class MessageController {
	
	private static final Logger log = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	ServletContext sc;
	
	@Resource
	BusinessFacade facade;
	
	
	
	
	
	

	@RequestMapping(value="/getVolunteerMessages", method = RequestMethod.GET)
	public @ResponseBody String getVolunteerMessages(@RequestParam Integer projectVolunteerId) {
		

	
		
		
		List<Message> messages = facade.getMessagesIncludedInAProjectVolunteer(projectVolunteerId);
		
		
		return constructMessageSting(messages);
		
		
		
				
		
	}
	
	private String constructMessageSting(List<Message> m){
		StringBuilder sb = new StringBuilder();
		sb.append("<table>");
		for(Message message:m){
			
			Integer userIdCreatedBy = message.getCreatedBy().getUserId();
			
			sb.append("<tr>");
			sb.append("<td><img class='profileimage' src='loadImage?userId=" + userIdCreatedBy + "'/></td>");
			sb.append("<td>" + message.getMessage()+ "</td>");
			sb.append("</tr>");
		}
		sb.append("</table>");
		
		return sb.toString();
	}
	
	
	

	
	
	
	
	
	
	@RequestMapping(value="/postMessageToProjectVolunteer", method = RequestMethod.GET)	
	public @ResponseBody String postMessageToProjectVolunteer(@RequestParam Integer projectVolunteerId, @RequestParam String message) {
		

		facade.postNewMessage(message, projectVolunteerId);
		
		
		List<Message> messages = facade.getMessagesIncludedInAProjectVolunteer(projectVolunteerId);
		
		
		return constructMessageSting(messages);
		
		
		
				
		
	}

	
	
	
	
	
	
	
	
	
}

