package com.ssafy.ddudu.model.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ssafy.ddudu.model.dto.User;

public interface UserService {
	List<User> getUsers();

	int insert(User user, MultipartFile file) throws IOException;

	boolean login(String userName, String userPassword);

	int update(User user);

	User getUserById(String userId);

	int delete(String userId);
}
