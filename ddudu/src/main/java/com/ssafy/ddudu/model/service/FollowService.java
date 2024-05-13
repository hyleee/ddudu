package com.ssafy.ddudu.model.service;

import java.util.List;

public interface FollowService {
	List<String> getFollowers(String userId);

	List<String> getFollowings(String userId);

	void followUser(String followerId, String followingId);

	void unfollowUser(String followerId, String followingId);

}
