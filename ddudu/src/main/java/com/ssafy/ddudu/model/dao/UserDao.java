package com.ssafy.ddudu.model.dao;

import java.util.List;

import com.ssafy.ddudu.model.dto.User;

public interface UserDao {
	
	List<User> getUsers();
	
	int insert(User user);


	User findByUserId(String userId);
}
