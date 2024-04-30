package com.king.model.dto;

public class CommentLike {

	private int commentId;
	private String userId;

	public CommentLike(int commentId, String userId) {
		super();
		this.commentId = commentId;
		this.userId = userId;
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
