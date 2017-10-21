package com.concretepage.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.concretepage.dao.IUserDAO;
import com.concretepage.entity.User;
@Service
public class UserService implements IUserService{
	@Autowired
	private IUserDAO userdao;
	@Override
	public User getUserById(String email, String phonenumber) {
		
		return userdao.getUserById(email, phonenumber);
	}

	@Override
	public boolean addUser(User user) {
		boolean success = false;
		if (!userdao.userExists(user.getEmail(), user.getPhonenumber())) {
			userdao.addUser(user);
			success = true;
		}
		return success;
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUser(String articleId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User getUser(User user) {
		return userdao.getUser(user);
	}

	

}
