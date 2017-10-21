package com.concretepage.service;


import com.concretepage.entity.User;

public interface IUserService {

	User getUserById(String email,String mobilenumber);
    boolean addUser( User user);
    void updateUser(User user);
    User getUser(User user);
    void deleteUser(String articleId);
    
}
