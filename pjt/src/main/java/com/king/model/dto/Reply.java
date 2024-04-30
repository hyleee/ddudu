package com.king.model.dto;

import java.util.Date;

public class Reply {

	private int replyId;
	private String userId;
	private String replyContent;
	private Date replyCreated;

	public Reply(int replyId, String userId, String replyContent, Date replyCreated, int commentId) {
		super();
		this.replyId = replyId;
		this.userId = userId;
		this.replyContent = replyContent;
		this.replyCreated = replyCreated;
		this.commentId = commentId;
	}

	private int commentId;

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

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public Date getReplyCreated() {
		return replyCreated;
	}

	public void setReplyCreated(Date replyCreated) {
		this.replyCreated = replyCreated;
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

}
