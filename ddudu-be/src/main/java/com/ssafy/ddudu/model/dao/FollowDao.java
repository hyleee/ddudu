package com.ssafy.ddudu.model.dao;

import java.util.List;

import com.ssafy.ddudu.model.dto.Follow;

public interface FollowDao {
	List<String> findFollowersByUserId(String userId);

	List<String> findFollowingsByUserId(String userId);

	void insertFollow(Follow follow);

	void deleteFollow(Follow follow);

}
