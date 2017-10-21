package com.concretepage.dao;



import java.sql.ResultSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.concretepage.endpoint.ArticleEndpoint;
import com.concretepage.entity.User;
@Transactional
@Repository
public class UserDAO implements IUserDAO{
	private static final Logger logger = LoggerFactory.getLogger(ArticleEndpoint.class);	
	
	@PersistenceContext	
	private EntityManager entityManager;
	@Override
	public User getUserById(String email, String mobilnumber) {
		
		String sql = "SELECT * FROM user WHERE email = ? OR phonenumber = ?";
		Query query = null;
		try{
			query =entityManager.createNativeQuery(sql,User.class).setParameter(1,email).setParameter(2, mobilnumber);
		}catch(Exception e){
			logger .info(e.getMessage());
		}
		System.out.println(query.getResultList().get(0).toString());
	
		return (User)query.getResultList().get(0);
	}

	@Override
	public User addUser(User user) {
		entityManager.persist(user);
		return user;
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteArticle(String articleId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean userExists(String email, String phonenumber) {
		String sql = "select count(*) from user where email = ? OR phonenumber = ?";
		int count = 0;
		try{
		count = entityManager.createNativeQuery(sql).setParameter(1,email).setParameter(2,phonenumber).getResultList().indexOf(1);
	}catch(Exception e){
		logger .info(e.getMessage());
	}
		return count > 0 ? true : false;
	}

	@Override
	public User getUser(User user) {
		return entityManager.find(User.class,user.getEmail());
		
	}

}
