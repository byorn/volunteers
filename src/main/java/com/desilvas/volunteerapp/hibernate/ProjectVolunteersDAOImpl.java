package com.desilvas.volunteerapp.hibernate;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.desilvas.volunteerapp.dao.ProjectVolunteersDAO;
import com.desilvas.volunteerapp.domain.Project;
import com.desilvas.volunteerapp.domain.ProjectVolunteer;
import com.desilvas.volunteerapp.domain.User;
import com.desilvas.volunteerapp.domain.TO.UserTO;


@Repository
public class ProjectVolunteersDAOImpl implements ProjectVolunteersDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private SimpleJdbcTemplate jdbcTemplate;

	public Integer projectVolunteer(Integer projectId, Integer userId) {

		if(projectId==null || userId==null){
			
			throw new IllegalArgumentException(
					"Project Id:" + projectId + "\n" + 
					"User Id: " + userId + "\n"  
				    
					);
		}
		

		Session session = sessionFactory.getCurrentSession();
		
		User user = new User(userId);
		Project project = new Project(projectId);
		ProjectVolunteer projectVolunteer = new ProjectVolunteer();
		
		projectVolunteer.setPostedBy(user);
		projectVolunteer.setProject(project);
		projectVolunteer.setSelected(false);
		
		
		session.save(projectVolunteer);
		
		
		return projectVolunteer.getProjectVolunteersId();
		
		
	}

	/*public List<ProjectVolunteer> getProjectVolunteersForUser(Integer userId, Integer projectId) {
		Session session = sessionFactory.openSession();
		List<?> result = session.createQuery("from ProjectVolunteer pv where pv.postedBy.userId = :userid" +
				" and pv.project.projectId = :projectid")
				.setParameter("userid", userId)
				.setParameter("projectid", projectId)
				.list();
		
		session.close();
		return (List<ProjectVolunteer>) result;
	}*/
	
	
	public Integer  getProjectVolunteerId(Integer projectId, Integer userId){
		
		
 	   String sql = "" +
 			   "SELECT pv.proj_vol_id FROM " +
 			   " project_volunteers pv " +  
 			   " where pv.project_id = ? " + 
 			   " and pv.user_id = ? " ;  
 			   
 	   
 	   List<Map<String, Object>> rows= jdbcTemplate.queryForList(sql,projectId, userId);
 	   
 	   if(rows.size()>1){
 		   throw new IllegalStateException("More than one record found in project_volunteers table");
 	   }
 	  
 	   if(rows.size()==1){
 		   Map<String, Object> map = rows.get(0);
 		   Long l = (Long) map.get("proj_vol_id");
 		   return Integer.valueOf(l.intValue());
 	   }
 	   
 	   return null;
		
	}
	
	
	
	 public Map<Project,List<ProjectVolunteer>> getVolunteerNamesForDashboard(Integer projectPosterUserId){
		   
		   
		 	   
		 	   String sql = "" +
		 			   "SELECT pv.proj_vol_id, pv.project_id,pv.selected, u.user_id, u.username FROM " +
		 			   " project_volunteers pv " +  
		 			   " inner join users u " +
		 			   " on pv.user_id=u.user_id " +  
		 			   " where pv.project_id in ( " + 
		 			   " select project_id from projects where " +  
		 			   " posted_by = ?) " ;
		 	   
		 	   List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql,projectPosterUserId);
		 			   
		 			   	   
		 	   for (Map<String, Object> row : rows) {
		 			
		 			
		 			Long projectId = (Long) row.get("project_id");
		 			
		 			Long userId = (Long) row.get("user_id");
		 			
		 			Long projectVolId = (Long) row.get("proj_vol_id");
		 			
		 			String userName = (String) row.get("username");
		 			
		 			Boolean selected = (Boolean) row.get("selected");
		 			
		 			
		 			
		 			
		 			
	 				UserTO userTO = new UserTO();
	 				userTO.setUserId(userId);
	 				userTO.setUserName(userName);
	 				userTO.setProjVolId(projectVolId);
	 				userTO.setSelected(selected==null?false:selected.booleanValue());
	 				
	 				
		 			
		 		}
		 	   
		 	   return null;
		    
		    
		 	   
		    }
	 
	 
	 public ProjectVolunteer getProjectVolunteer(Integer projectVolunteerId){
		 
		 Session session = sessionFactory.openSession();
		 
		 return (ProjectVolunteer) session.get(ProjectVolunteer.class, projectVolunteerId);
		
	 }

	
	 public void updateProjectVolunteer(ProjectVolunteer projectVolunteer){
		 Session session = sessionFactory.getCurrentSession();
		 session.update(projectVolunteer);
		 
	 }
	 
	 
	 public void unselectAllVolunteersForProject(Integer projectId){
			Session session = sessionFactory.getCurrentSession();

		 String hqlUpdate = "update ProjectVolunteer pv set pv.selected = :selected where pv.project.projectId = :projectid";

		 session.createQuery(hqlUpdate)
        .setBoolean("selected", false)
        .setInteger("projectid", projectId)
        .executeUpdate();
		 
		 
	 }
	 
	 
	 
	 public List<ProjectVolunteer> getProjectsVolunteeredBy(Integer userId){
		 
		 
		 
			Session session = sessionFactory.openSession();

			String hqlUpdate = "from ProjectVolunteer pv where pv.postedBy.userId=:userId";
			
			
			List<ProjectVolunteer> pvs = session.createQuery(hqlUpdate).setParameter("userId", userId).list();
	 
			return pvs;
	 }

}
