package com.king.model.dto;

import java.util.Date;

public class Comment {

	private int commentId;
	private Date commentCreated;
	private String commentContent;
	private String userId;
	private int articleId;
	public Comment(int commentId, Date commentCreated, String commentContent, String userId, int articleId) {
		super();
		this.commentId = commentId;
		this.commentCreated = commentCreated;
		this.commentContent = commentContent;
		this.userId = userId;
		this.articleId = articleId;
	}
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public Date getCommentCreated() {
		return commentCreated;
	}
	public void setCommentCreated(Date commentCreated) {
		this.commentCreated = commentCreated;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getArticleId() {
		return articleId;
	}
	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

}
