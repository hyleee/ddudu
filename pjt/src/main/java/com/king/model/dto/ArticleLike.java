package com.king.model.dto;

public class ArticleLike {

	private int articleId;
	private String userId;

	public ArticleLike(int articleId, String userId) {
		super();
		this.articleId = articleId;
		this.userId = userId;
	}

	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
