package com.king.model.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.king.model.dao.UserDao;
import com.king.model.dto.User;

public interface UserService {
	
	int insert(User user);

	boolean login(String username, String password);


}
