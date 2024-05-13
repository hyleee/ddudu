package com.ssafy.ddudu.model.dto;

public class Follow {

	private String followerId;
	private String followingId;

	public Follow() {
	}

	public Follow(String followerId, String followingId) {
		super();
		this.followerId = followerId;
		this.followingId = followingId;
	}

	public String getFollowerId() {
		return followerId;
	}

	public void setFollowerId(String followerId) {
		this.followerId = followerId;
	}

	public String getFollowingId() {
		return followingId;
	}

	public void setFollowingId(String followingId) {
		this.followingId = followingId;
	}

}
