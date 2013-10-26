package com.desilvas.volunteerapp.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.desilvas.volunteerapp.dao.ProjectDAO;
import com.desilvas.volunteerapp.domain.Project;
import com.desilvas.volunteerapp.domain.User;


@Repository
public class ProjectDAOImpl implements ProjectDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private SimpleJdbcTemplate jdbcTemplate;
	
	

	public void saveProject(Project project) {

		Session session = sessionFactory.getCurrentSession();
		
		session.save(project);
				
	}

	
	
	public List<Project> getProjects() {
		
		Session session = sessionFactory.openSession();
			List<?> result = session.createQuery("from Project").list();
		session.close();
		
		
		
		return (List<Project>) result;
		
	}

	
	
	public List<Project> getProjects(int from, int pageSize) {
		
		
		
		Session session = sessionFactory.openSession();
			List<?> result = session.createQuery("from Project")
					.setFirstResult(from)
					.setMaxResults(pageSize)
					.list();
		
		
		
			return (List<Project>) result;
		
				
	}

	
	public Long getCountOfProjects() {
		
		Session session = sessionFactory.openSession();
		
		return (Long) session.createQuery("select count(*) from Project").uniqueResult();
					
				
	}
	



	public Project getProject(Integer projectId) {
		Session session = sessionFactory.openSession();
		return (Project) session.get(Project.class, projectId);
	}


   
   
   public void updateProjectStatus(String status, Integer projectId){
	   Session session = sessionFactory.getCurrentSession();
	
	   String hqlUpdate = "update Project p set p.status = :status where p.projectId = :projectid";

		 session.createQuery(hqlUpdate)
        .setString("status", status)
        .setInteger("projectid", projectId)
        .executeUpdate();
	   
   }




   public List<Project> getProjectsPosted(User user) {
	   
	   
	   Session session = sessionFactory.openSession();
	   List<?> result = session.createQuery("from Project p where p.postedBy.userId=:userid").setParameter("userid", user.getUserId()).list();
	   return (List<Project>) result;
   }
   
   
  
	

}
