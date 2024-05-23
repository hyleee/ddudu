package com.ssafy.ddudu.model.dao;

import com.ssafy.ddudu.model.dto.Auth;

public interface AuthDao {
    Auth findRefreshToken(String userId);
    void saveRefreshToken(Auth auth);
    void deleteRefreshToken(String userId); 
}
