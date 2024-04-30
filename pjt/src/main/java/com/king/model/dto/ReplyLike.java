package com.king.model.dto;

public class ReplyLike {

	private int replyId;
	private String userId;

	public ReplyLike(int replyId, String userId) {
		super();
		this.replyId = replyId;
		this.userId = userId;
	}

	public int getReplyId() {
		return replyId;
	}

	public void setReplyId(int replyId) {
		this.replyId = replyId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
