package com.desilvas.volunteerapp.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.desilvas.volunteerapp.dao.MessageDAO;
import com.desilvas.volunteerapp.domain.Message;


@Repository
public class MessageDAOImpl implements MessageDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private SimpleJdbcTemplate jdbcTemplate;

	public void createMessage(Message message) {
		
		Session session = sessionFactory.getCurrentSession();
		
		session.save(message);
				
	}
	
	
	
	public List<Message> getMessagesFor(Integer projectVolId){
		
		
		Session session = sessionFactory.openSession();
		List<?> result = session.createQuery("from Message where projectVolunteerId = :pvid")
				.setParameter("pvid", projectVolId)
				.list();
		
		session.close();
	
	
	
		return (List<Message>) result;
		
		
	}


	
	

}
