package com.desilvas.volunteerapp.web;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.desilvas.volunteerapp.domain.Category;
import com.desilvas.volunteerapp.domain.Project;
import com.desilvas.volunteerapp.domain.ProjectVolunteer;
import com.desilvas.volunteerapp.domain.User;
import com.desilvas.volunteerapp.domain.UserSkills;
import com.desilvas.volunteerapp.domain.TO.PageTO;
import com.desilvas.volunteerapp.service.BusinessFacade;
import com.desilvas.volunteerapp.service.BusinessResponse;
import com.desilvas.volunteerapp.util.Util;
import com.desilvas.volunteerapp.util.VolyConstants;


/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger log = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	ServletContext sc;
	
	@Resource
	BusinessFacade facade;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		log.info("Welcome home! the client locale is "+ locale.toString());
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "welcome";
	}
	
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String welcome(Model model) {
		
				
		return "welcome";
	}
	
	
	@RequestMapping(value = "/post", method = RequestMethod.GET)
	public String post(Model model) {
		
		List<Category> categories = facade.getCategories();
		
		model.addAttribute("categoryList",categories);
				
		return "post";
	}
	
	@RequestMapping(value = "/dashboardRecruiter", method = RequestMethod.GET)
	public String dashboardRecruiter(Model model) {
		
		
		
		List<Project> projectsPosted = facade.getProjectsPosted();
		model.addAttribute("projectsPostedList", projectsPosted);
		
				
		return "dashboard-recruiter";
	}
	
	
	
	
	@RequestMapping(value = "/volunteer", method = RequestMethod.GET)
	public String getListOfOpenProjects(Model model,@RequestParam String pageId,HttpServletRequest req) {
		
		
            
                System.out.println("came here");
		
		PageTO pageTO = new PageTO();
		pageTO.setPageSize(10);
		pageTO.setStartFrom((Integer.valueOf(pageId)-1)*10);
		
					
		PageTO page = facade.getPagedProjectList(pageTO);
		
		model.addAttribute("projectList", page.getRecords());
		
		
		model.addAttribute("categories", facade.getCategories());
		
		
		model.addAttribute("pages", constructNumberOfPages(page.getTotalRecords(),page.getPageSize()));
		
		return "search";
	}
	
	private List<String> constructNumberOfPages(int totalRecords, int pageSize){
		
		List<String> pages = new ArrayList<String>();
		int totalPages = totalRecords/pageSize;
		
		
		for(int i = 1 ; i<=totalPages;i++){
			pages.add(String.valueOf(i));
		}
		
		pages.add(String.valueOf(totalPages+1));
		
		return pages;
		
		
	}
	
	
	
	@RequestMapping(value="/loginfailed", method = RequestMethod.GET)
	public String loginerror(ModelMap model) {
 
		model.addAttribute("error", "true");
		return "login";
 
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logout(ModelMap model) {
 
		return "login";
 
	}
	
	
	@RequestMapping(value="/postMessage", method = RequestMethod.GET)
	public @ResponseBody JSONResponse postMessage(HttpServletRequest hr,@Valid VolunteerForProjectView obj,BindingResult result,ViewObject userView) {
		

		boolean hasErrors = false;
		
		JSONResponse jsonResponse = new JSONResponse();
				
			
		if(result.hasErrors()){
						
				List<FieldError> fes = result.getFieldErrors();
				
				for(FieldError e:fes){
					jsonResponse.getFieldMap().put(e.getField(), e.getDefaultMessage());
						
				}
				
			  hasErrors=true;
		}
		
		if(!userView.isValidView()){
			
			 jsonResponse.getFieldMap().putAll(userView.getErrors());
			  hasErrors=true;
			
		}
		
		
		if(hasErrors){
			return jsonResponse;
		}
		
		
		
				
		//back end call
		BusinessResponse response =  facade.volunteerForProject(hr, obj.getProjectId(), obj.getMessageText(),
				userView.getUserName(), userView.getPassword(), userView.isExistingUser());
		
		
		//return response
		if(!response.isSuccess()){
					
				jsonResponse.getFieldMap().put(VolyConstants.JSP_FIELDS.errorResponse.toString(), Util.toHTMLList(response.getErrorMessages()));
					
					return jsonResponse;
		}
		 
				
		jsonResponse.setSuccess(true);
		jsonResponse.getFieldMap().put(VolyConstants.JSP_FIELDS.successResponse.toString(), Util.toHTMLList(response.getSuccessMessages()));
					
		return jsonResponse;
		
 
	}
	
	
	

	@RequestMapping(value="/getPostedProjectDetails", method = RequestMethod.GET)
	public @ResponseBody String getPostedProjectDetails(ViewObject obj) {
			
				
		Project project = facade.getProject(obj.getProjectId());
		
		StringBuilder htmlStringResponse = new StringBuilder();
		
				
		htmlStringResponse.append("<h1>"+project.getProjectTitle()+"</h1><br/>");
		htmlStringResponse.append("<h3> Posted By: "+project.getPostedBy().getUsername()+"</h3><br/>");
		htmlStringResponse.append("<h3> Posted Date: "+project.getPostedDate()+"</h3><br/>");
		htmlStringResponse.append("<p> " + project.getProjectDetail() +"</p><br/>");
		
		
		return htmlStringResponse.toString();
 
	}
	
	
	
	
	
	
	
	@RequestMapping(value="/postProject", method = RequestMethod.POST)
	public @ResponseBody JSONResponse postProject(HttpServletRequest hr, @Valid PostNewProjectView obj, BindingResult result, ViewObject userView) {

		boolean hasErrors = false;
		
		JSONResponse jsonResponse = new JSONResponse();
				
			
		if(result.hasErrors()){
						
				List<FieldError> fes = result.getFieldErrors();
				
				for(FieldError e:fes){
					jsonResponse.getFieldMap().put(e.getField(), e.getDefaultMessage());
						
				}
				
			  hasErrors=true;
		}
		
		if(!userView.isValidView()){
			
			 jsonResponse.getFieldMap().putAll(userView.getErrors());
			  hasErrors=true;
			
		}
		
		
		if(hasErrors){
			return jsonResponse;
		}
		
		
				
		
		//back end call
		BusinessResponse response = facade.postNewProject(hr, userView.getUserName(), userView.getPassword(),
				userView.isExistingUser(), obj.getProjectTitle(), obj.getProjectDetail(), obj.getCategoryCode());
		
		
		//return response
		if(!response.isSuccess()){
			
			jsonResponse.getFieldMap().put(VolyConstants.JSP_FIELDS.errorResponse.toString(), Util.toHTMLList(response.getErrorMessages()));
			
			return jsonResponse;
		}
 
		
		jsonResponse.setSuccess(true);
	
		jsonResponse.getFieldMap().put(VolyConstants.JSP_FIELDS.successResponse.toString(), Util.toHTMLList(response.getSuccessMessages()));
			
		return jsonResponse;
		
		
		 
	}
	
	
	
	
	
	@RequestMapping(value="/dashboardVolunteer", method = RequestMethod.GET)
	public String dashboardVolunteer(ModelMap model, HttpServletRequest request) {
 

		String userId = request.getParameter("userId");
			
	
		
		List<ProjectVolunteer> listProjects;
		
		if(userId==null){
			listProjects = facade.getProjectsVolunteeredBy(facade.getLoggedInUserId());
		}else{
			listProjects = facade.getProjectsVolunteeredBy(Integer.valueOf(userId));
		}
		
		
		model.addAttribute(VolyConstants.JSP_COLLECTION_FIELDS.projectsVolunteered.toString(), listProjects);
					
	
		return "dashboard-volunteer";
 
	}
	
	
	@RequestMapping(value="/dashboardMyProfile", method = RequestMethod.GET)
	public String dashboardMyProfile(ModelMap model) {
 
		User user = facade.getLoggedInUserObject();
		
		
		UserSkills skills = facade.getUserSkills(user.getUserId());
		
		model.addAttribute("displayName", user.getDisplayname());
		if(skills!=null){
			model.addAttribute("userskills", skills.getSkills());		
			model.addAttribute("reasonForVolunteer", skills.getVolunteerReason());
		}
		
		return "dashboard-myprofile";
 
	}
	
	
	
	
	
	

	
	@RequestMapping(value="/updateProfile", method = RequestMethod.POST)
	public @ResponseBody String updateProfile(UserDetailsViewObject obj)  {
		
		
		
		BusinessResponse response = facade.updateProfile(obj.getDisplayName(), obj.getPassword());
		
				
		if(response.isSuccess()){
			return response.getSuccessMessages().get(0);
		}else{
			return "Failed to update user details! Please try again!";
		}
				
		
		
	}
	
	
	
	
	@RequestMapping(value="/selectVolunteer", method = RequestMethod.POST)
	public @ResponseBody String selectVolunteer(@RequestParam Integer projectVolunteerId)  {
		
		
		
		BusinessResponse response = facade.selectVolunteer(projectVolunteerId);
		
				
		if(response.isSuccess()){
			return response.getSuccessMessages().get(0);
		}else{
			return "Failed to update user details! Please try again!";
		}
				
	
		
	}

	
	

	
	@RequestMapping(value="/publicProfile", method = RequestMethod.GET)
	public String publicProfile()  {
		
			
			return "public-profile";
					
	
		
	}

	
	
	
	
	@RequestMapping(value="/saveUserSkills", method = RequestMethod.POST)
	public @ResponseBody String saveUserSkills(@RequestParam String skills)  {
		
		facade.saveUserSkills(skills);
		
		return "successfully saved";
			
	}
	
	
	
	@RequestMapping(value="/saveReasonForVolunteer", method = RequestMethod.POST)
	public @ResponseBody String saveReasonForVolunteer(@RequestParam String reasonForVolunteer)  {
		
		facade.saveReasonForVolunteer(reasonForVolunteer);
		
		return "successfully saved";
			
	}
	
	
	
	
	
	
	
	
}

