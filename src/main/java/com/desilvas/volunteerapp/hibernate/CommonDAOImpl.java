package com.desilvas.volunteerapp.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.desilvas.volunteerapp.dao.CommonDAO;
import com.desilvas.volunteerapp.dao.UserDAO;
import com.desilvas.volunteerapp.domain.Category;
import com.desilvas.volunteerapp.domain.User;


@Repository
public class CommonDAOImpl implements CommonDAO{
	
	@Autowired
	private SessionFactory sessionFactory;

	public List<Category> getCategories() {
		Session session = sessionFactory.openSession();
		List<?> result =	 
				session.createQuery("from Category").list();
		
		session.close();
		
		
				
		return (List<Category>) result;
	}

	
	
	

}
