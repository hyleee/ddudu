package com.ssafy.ddudu.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ddudu.model.dao.UserDao;
import com.ssafy.ddudu.model.dto.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public List<User> getUsers() {
		return userDao.getUsers();
	}

	@Override
	public int insert(User user) {
		return userDao.insertUser(user);
	}

	@Override
	public boolean login(String userId, String userPassword) {
		User user = userDao.findByUserId(userId);
		if (user != null) {
			return userPassword.equals(user.getUserPassword());
		}
		return false;
	}

	@Override
	public int update(User user) {
		return userDao.updateUser(user);
	}

	@Override
	public User getUserById(String userId) {
		return userDao.findByUserId(userId);
	}

	@Override
	public int delete(String userId) {
		return userDao.deleteUser(userId);
	}

}
