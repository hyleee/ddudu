package com.ssafy.ddudu.model.service;


import com.ssafy.ddudu.model.dto.Auth;

public interface AuthService {
    void saveRefreshToken(String userId, String refreshToken);
    Auth findRefreshToken(String userId);
}
