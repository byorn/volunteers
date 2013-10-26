package com.desilvas.volunteerapp.dao;

import java.util.List;

import junit.framework.Assert;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.desilvas.volunteerapp.domain.Project;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/spring-database.xml"})
public class ProjectDAOTest {

	


	@Autowired
	private SessionFactory sessionFactory;
	
	
	
	
	@Test
	public void testGetProjects(){

		Assert.assertNotNull(sessionFactory);
		
		Session session = sessionFactory.openSession();
		List<Project> result = session.createQuery("from Project").list();
		for(Project p : result){
			System.out.println(p.getProjectVolunteers().size());
		}
		
		
	}
	
}
