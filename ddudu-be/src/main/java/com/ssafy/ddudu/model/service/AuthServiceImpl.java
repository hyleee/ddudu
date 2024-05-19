package com.ssafy.ddudu.model.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ddudu.model.dao.AuthDao;
import com.ssafy.ddudu.model.dto.Auth;
import com.ssafy.ddudu.model.dto.User;


@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthDao authDao;

//    @Override
//    public User loginUser(User user) {
//        // 로그인 로직 구현 (예: 데이터베이스 조회)
//        // 여기에 실제로 유저를 데이터베이스에서 확인하는 로직을 추가하세요
//        return user;
//    }
    
    @Override
    public void saveRefreshToken(String userId, String refreshToken) {
        Auth auth = new Auth();
        auth.setUserId(userId);
        auth.setRefreshToken(refreshToken);
        authDao.saveRefreshToken(auth);
    }

    @Override
    public Auth findRefreshToken(String userId) {
        return authDao.findRefreshToken(userId);
    }
}
