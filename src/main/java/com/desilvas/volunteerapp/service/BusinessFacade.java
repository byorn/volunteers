package com.desilvas.volunteerapp.service;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.desilvas.volunteerapp.dao.CommonDAO;
import com.desilvas.volunteerapp.dao.MessageDAO;
import com.desilvas.volunteerapp.dao.ProjectDAO;
import com.desilvas.volunteerapp.dao.ProjectVolunteersDAO;
import com.desilvas.volunteerapp.dao.UserDAO;
import com.desilvas.volunteerapp.domain.Category;
import com.desilvas.volunteerapp.domain.Message;
import com.desilvas.volunteerapp.domain.Project;
import com.desilvas.volunteerapp.domain.ProjectVolunteer;
import com.desilvas.volunteerapp.domain.User;
import com.desilvas.volunteerapp.domain.UserImage;
import com.desilvas.volunteerapp.domain.UserSkills;
import com.desilvas.volunteerapp.domain.TO.PageTO;
import com.desilvas.volunteerapp.util.Util;
import com.desilvas.volunteerapp.util.VolyConstants;

@Service
public class BusinessFacade {

	
	@Autowired
	ProjectDAO projectDAO;
	
	@Autowired
	UserDAO userDAO;
	
	
	@Autowired
	ProjectVolunteersDAO projectVolunteersDAO;
	
	@Autowired
	CommonDAO commonDAO;
	
	@Autowired
	MessageDAO messageDAO;
	
	
	@Resource(name = "authenticationManager")
	private AuthenticationManager authenticationManager;
	
	
	@Transactional
	public BusinessResponse volunteerForProject(HttpServletRequest hr,
									 Integer projectId, 
								      String messageText,
								      String userName, 
								      String password, 
								      boolean isExistingUser) {
		

		BusinessResponse businessResponse = new BusinessResponse();
		
		Integer userId = null;
		
		
		if(!Util.isUserLoggedIn()){
			
				if(isExistingUser){
					
					if(!authenticateUser(hr,userName, password)){
						businessResponse.addErrorMessage("Could not authenticate user, please login or provide valid credentials");
						return businessResponse;
					}else{
						userId = getLoggedInUserId();
					}
				
				}else{
					if(null == userDAO.getUserForUserName(userName)){
						userId = createNewUser(userName, password,true);
						businessResponse.addSuccessMessage("An email has been sent to you! Please confirm <br>");
					}else{
						businessResponse.addSuccessMessage("This user name is already taken");
						
					}
					
					
					
				}
		
		}else{
			       userId = getLoggedInUserId();
		}
		
		Integer projectVolunteerId = projectVolunteersDAO.getProjectVolunteerId(projectId, userId);
		
		if(projectVolunteerId==null){
			projectVolunteerId = projectVolunteersDAO.projectVolunteer(projectId, userId);
		}
		
		
		Message message = new Message();
		message.setMessage(messageText);
		
		User user = new User(userId);
		
		message.setCreatedBy(user);
		message.setProjectVolunteerId(projectVolunteerId);
		
		messageDAO.createMessage(message);
		
		businessResponse.addSuccessMessage("Your message has been posted to the project owner");
		
		return businessResponse;
		
	}

	
	
	
	@Transactional
	private Integer createNewUser(String userName, String password, boolean enabled){
		
		User user = new User();
		user.setUsername(userName);
		user.setPassword(password);
		user.setEnabled(enabled);
		user.setDisplayname(userName);
		userDAO.saveUser(user);
		return user.getUserId();

	}
	
		
	@Transactional
	public BusinessResponse postNewProject(HttpServletRequest hr,
			String userName, String password, boolean isExistingUser, 
			String projectTitle, String projectDescription, String categoryCode){
	
		BusinessResponse response = new BusinessResponse();
		
		
		Integer userId = null;
		
		if(!Util.isUserLoggedIn()){
			
				if(isExistingUser){
					
					if(!authenticateUser(hr,userName, password)){
						
						response.addErrorMessage("Could not authenticate user, please login or provide valid credentials");
						return response;
					}else{
						userId = getLoggedInUserId();
					}
				
				}else{
					
					try {
	
						if(userDAO.getUserForUserName(userName)!=null)
						{
							response.addErrorMessage("This user already exists. Please select option: 'existing user'");
							return response;
						}
						
						userId = createNewUser(userName, password, false);
	
					} catch (org.hibernate.exception.ConstraintViolationException cve) {
						
						throw new IllegalStateException("Trying to create a user record, when it already exists!");

					}		
					
				}
		
		}else{
			userId = getLoggedInUserId();
		}
		
		Project project = new Project();
		Category cat = new Category();
		cat.setCategoryCode(categoryCode);
		project.setCategory(cat);
		
		User user = new User();
		user.setUserId(userId);
		project.setPostedBy(user);
		project.setPostedDate(new Date());
		project.setProjectTitle(projectTitle);
		project.setProjectDetail(projectDescription);
		project.setStatus(VolyConstants.PROJECT_STATES.NEW.toString());
		projectDAO.saveProject(project);

		
		
		if (isExistingUser || Util.isUserLoggedIn()) {
			response.addSuccessMessage("Your project is posted now! Volunteers will contact you after this. To see who responded to your post, just log in to your dashboard! Have fun!");
		} else {
			response.addSuccessMessage("You'll be getting an email shortly. The email has a link. Just click it, and you'll be automatically logged in to your dashboard.");
		}
			
		
		
		return response;
		
			
	}
	
	
	
	

private boolean doAutoLogin(HttpServletRequest hr, String username, String password) {

   
        // Must be called from request filtered by Spring Security, otherwise SecurityContextHolder is not updated
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        token.setDetails(new WebAuthenticationDetails(hr));
        Authentication authentication;
        try{
        	authentication = authenticationManager.authenticate(token);
        }catch(AuthenticationException ae){
        	return false;
        }
        
        
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);
       
     
        hr.getSession().setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
        return true;
   
}
	


	private boolean authenticateUser(HttpServletRequest hr, String username,
			String password) {

		// Must be called from request filtered by Spring Security, otherwise
		// SecurityContextHolder is not updated
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
				username, password);
		token.setDetails(new WebAuthenticationDetails(hr));

		
		
		try {
			 Authentication authentication = authenticationManager.authenticate(token);
			
			 SecurityContext securityContext = SecurityContextHolder.getContext();
		        securityContext.setAuthentication(authentication);
			
		} catch (AuthenticationException ae) {
			ae.printStackTrace();
			return false;
		}

		return true;

	}	
	
	
	
	public Integer getLoggedInUserId(){
		
		final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		
		User user = userDAO.getUserForUserName(currentUser);
		
		
		
		return user.getUserId();
	}


	public User getLoggedInUserObject(){
		
		final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		
		User user = userDAO.getUserForUserName(currentUser);
		
		
		
		return user;
	}





	public List<Category> getCategories() {
		return commonDAO.getCategories();
	}






	

	public List<Project> getProjects() {

		return projectDAO.getProjects();
		
	}
	
	
	public List<Project> getProjectsPosted() {
		
		User user = getLoggedInUserObject();

		return projectDAO.getProjectsPosted(user);
		
	}





	


	public Project getProject(Integer projectId) {

			return projectDAO.getProject(projectId);
	}




	
	
	public PageTO getPagedProjectList(PageTO pageTO){
		
		List<Project> results = projectDAO.getProjects(pageTO.getStartFrom(), pageTO.getPageSize());
		Long count = projectDAO.getCountOfProjects();
		
		pageTO.setTotalRecords(count.intValue());
		pageTO.setRecords(results);
		
		
		return pageTO;
		
	}

	
	
	
	
	
	
	
	public List<Message> getMessagesIncludedInAProjectVolunteer(Integer projVolId){
		
		
		return messageDAO.getMessagesFor(projVolId);
		
		
		
		
	}
	
	
	@Transactional
	public void postNewMessage(String messageTxt, Integer projVolId){
		
		Message message = new Message();
		message.setCreatedBy(new User(getLoggedInUserId()));
		message.setMessage(messageTxt);
		message.setProjectVolunteerId(projVolId);
		
		messageDAO.createMessage(message);
		
	}
	
	
	
	public byte[] getImage(Integer userId){
		UserImage userImage = userDAO.getUserImage(userId);
		
		byte[] imageBytes = null;
		try {
			
			imageBytes = userImage.getImage().getBytes(1, (int) userImage.getImage().length());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return imageBytes;
	}
	
	
	
	@Transactional
	public BusinessResponse uploadImage(byte[] image, boolean isCropped){
	
		BusinessResponse br = new BusinessResponse();
		
		
		
		
		UserImage ui = userDAO.getUserImage(getLoggedInUserId());
		if(ui==null){
			ui = new UserImage();
			ui.setUserId(getLoggedInUserId());
		}
		
		Blob blob = null;
		try {
			blob = new javax.sql.rowset.serial.SerialBlob(image);
		} catch (SerialException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		if(isCropped){
			ui.setImage(blob);	
		}
		
		ui.setUploadedImage(blob);
		
		
		ui.setImage(blob);
		
		userDAO.uploadImage(ui);
		
		br.addSuccessMessage("file uploaded");
		return br;
	}
	
	
	
	
	
	
	
	
	@Transactional
	public BusinessResponse updateProfile(String displayName, String password){

		BusinessResponse br = new BusinessResponse();
		
		final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userDAO.getUserForUserName(currentUser);
		user.setDisplayname(displayName);
		user.setPassword(password);
		
		userDAO.updateUser(user);
		
		br.addSuccessMessage("Updated user profile");
		return br;
	}
	
	

	
	
	@Transactional
	public BusinessResponse selectVolunteer(Integer projectVolunteerId){
		
		ProjectVolunteer projectVolunteer = projectVolunteersDAO.getProjectVolunteer(projectVolunteerId);
		
	
		projectVolunteersDAO.unselectAllVolunteersForProject(projectVolunteer.getProject().getProjectId());
		
		projectVolunteer.setSelected(true);
		projectVolunteersDAO.updateProjectVolunteer(projectVolunteer);
		
		projectDAO.updateProjectStatus(VolyConstants.PROJECT_STATES.ASN.toString(), projectVolunteer.getProject().getProjectId());
		
		
		return BusinessResponse.success();
	}
	
	
	public List<ProjectVolunteer> getProjectsVolunteeredBy(Integer userId){
		
		List<ProjectVolunteer> pvs = projectVolunteersDAO.getProjectsVolunteeredBy(userId);
		
		return pvs;
	}
	
	@Transactional
	public void saveUserSkills(String skills){
		
		UserSkills usk = new UserSkills();
		usk.setSkills(skills);
		usk.setUserId(getLoggedInUserId());
		
		userDAO.saveUserSkills(usk);
		
	}
	
	
	public UserSkills getUserSkills(Integer userId){
		UserSkills usk = userDAO.getUserSkills(userId);
		
		return usk;
	}
	
	
	@Transactional
	public void saveReasonForVolunteer(String reasonForVolunteer){
		
		UserSkills usk = userDAO.getUserSkills(getLoggedInUserId());
		usk.setVolunteerReason(reasonForVolunteer);
		
		userDAO.saveUserSkills(usk);
	}
	
	
	
}
