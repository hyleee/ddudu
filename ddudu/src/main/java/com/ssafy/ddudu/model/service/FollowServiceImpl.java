package com.ssafy.ddudu.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ddudu.model.dao.FollowDao;
import com.ssafy.ddudu.model.dto.Follow;

@Service
public class FollowServiceImpl implements FollowService {

	@Autowired
	private FollowDao followDao;

	@Override
	public List<String> getFollowers(String userId) {
		return followDao.findFollowersByUserId(userId);
	}

	@Override
	public List<String> getFollowings(String userId) {
		return followDao.findFollowingsByUserId(userId);
	}

	@Override
	public void followUser(String followerId, String followingId) {
		Follow follow = new Follow(followerId, followingId);
		followDao.insertFollow(follow);
	}

	@Override
	public void unfollowUser(String followerId, String followingId) {
		Follow follow = new Follow(followerId, followingId);
		followDao.deleteFollow(follow);
	}
}
