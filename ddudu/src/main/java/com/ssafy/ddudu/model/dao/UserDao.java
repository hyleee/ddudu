package com.ssafy.ddudu.model.dao;

import java.util.List;

import com.ssafy.ddudu.model.dto.User;

public interface UserDao {
	
	List<User> getUsers();

	User findByUserId(String userId);
	
	int insertUser(User user);

	User selectOne(String userId);

	int updateUser(User user);

	int deleteUser(String userId);

}
