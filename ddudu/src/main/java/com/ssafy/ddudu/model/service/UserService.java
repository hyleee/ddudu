package com.ssafy.ddudu.model.service;

import java.util.List;

import com.ssafy.ddudu.model.dto.User;

public interface UserService {
	List<User> getUsers();

	int insert(User user);

	boolean login(String userName, String userPassword);
}
