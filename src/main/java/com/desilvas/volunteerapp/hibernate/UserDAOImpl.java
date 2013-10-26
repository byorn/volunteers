package com.desilvas.volunteerapp.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.desilvas.volunteerapp.dao.UserDAO;
import com.desilvas.volunteerapp.domain.User;
import com.desilvas.volunteerapp.domain.UserFeedBack;
import com.desilvas.volunteerapp.domain.UserImage;
import com.desilvas.volunteerapp.domain.UserSkills;


@Repository
public class UserDAOImpl implements UserDAO{
	
	@Autowired
	private SessionFactory sessionFactory;

	
	public void saveUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.save(user);
		
	}

	public User getUserForUserName(String userName) {

		Session session = sessionFactory.openSession();
	
		List<?> result =	 
			session.createQuery("from User u where u.username = :username")
			.setParameter("username",userName).list();
	
		
		session.close();
		
		
		if(result.size()==0 || result.size()>1){
			return null;
		}
			
		return (User) result.get(0);
	}
	
	

	
	public void uploadImage(UserImage ui){
		
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(ui); 
		
	}
	
	
	public UserImage getUserImage(Integer userId){
		
		Session session = sessionFactory.openSession();
		return (UserImage) session.get(UserImage.class, userId);
	
	}
	
	
	public void updateUser(User user){
		
		Session session = sessionFactory.getCurrentSession();
		session.update(user);
		
	}
	
	
	public List<UserFeedBack> getUserFeedback(Integer userId){
		Session session = sessionFactory.openSession();
		
		List<?> result =	 
				session.createQuery("from UserFeedBack u where u.user.userId = :userid")
				.setParameter("userid",userId).list();
		
			
		session.close();
		return (List<UserFeedBack>) result;	
			
		
	}
	
	
	
	public void saveUserSkills(UserSkills us){
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(us);
		
	}
	
	
	
	public UserSkills getUserSkills(Integer userId){
		
		Session session = sessionFactory.openSession();
		
		UserSkills usk = (UserSkills) session.get(UserSkills.class, userId);
		
		session.close();
		
		return usk;
		
	}
	
	public void createUserFeedBack(UserFeedBack ufb){
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(ufb);
	}
		

}
