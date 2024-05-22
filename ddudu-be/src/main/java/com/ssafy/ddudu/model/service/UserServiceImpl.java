package com.ssafy.ddudu.model.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.ddudu.model.dao.UserDao;
import com.ssafy.ddudu.model.dto.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Value("${upload.folder.path}")
    private String uploadFolderPath;
	
	@Autowired
	private ResourceLoader resLoader;

	@Override
	public List<User> getUsers() {
		return userDao.getUsers();
	}

	@Override
	public int insert(User user, MultipartFile file) throws IOException {
		return fileHandling(user, file);
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
	
	@Override
    public List<User> findByUserName(String userName) {
        return userDao.findByUserName(userName);
    }
	
	 private int fileHandling(User user, MultipartFile file) throws IOException {
	        // 절대 경로 사용
//	        String staticFolderPath = "/Users/jaesunglee/db_images";
	        Path staticFolder = Paths.get(uploadFolderPath);

	        // 폴더가 존재하지 않으면 생성
	        if (!Files.exists(staticFolder)) {
	            Files.createDirectories(staticFolder);
	        }

	        // 파일이 존재하고 크기가 0보다 큰 경우에만 처리
	        if (file != null && file.getSize() > 0) {
	            // 새로운 파일 이름 생성
	            String newFileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
	            user.setUserProfile(newFileName);

	            // 파일을 저장
	            Path targetFile = staticFolder.resolve(newFileName);
	            file.transferTo(targetFile.toFile());
	        }

	        return userDao.insertUser(user);
	    }
	
}
