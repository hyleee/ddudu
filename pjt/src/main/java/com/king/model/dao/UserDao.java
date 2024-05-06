package com.king.model.dao;

import java.util.List;

import com.king.model.dto.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {

    int insert(User user);


    User findByUserId(String userId);

}
