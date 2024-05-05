package com.king.model.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.king.model.dao.UserDao;
import com.king.model.dto.User;

public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public int insert(User user) {
		return userDao.insert(user);
	}

	@Override
	public boolean login(String username, String password) {
		User user = userDao.findByUsername(username);
		if(user!=null) {
			return password.equals(user.getPassword());
		}
		return false;
	}
	
	

}
