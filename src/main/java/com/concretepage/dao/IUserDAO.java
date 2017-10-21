package com.concretepage.dao;


import com.concretepage.entity.User;

public interface IUserDAO {
	   
	  
	     User getUserById(String email,String phonenumber);
	     User addUser( User user);
	     User getUser( User user);
	     void updateUser(User user);
	     void deleteArticle(String articleId);
	     boolean userExists(String email,String phonenumber);
}
