package com.king.model.dao;

import java.util.List;

import com.king.model.dto.User;

public interface UserDao {

    int insert(User user);


    User findByUsername(String username);

}
