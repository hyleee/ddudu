package com.king.model.dto;

import java.util.Date;

public class Article {

	private int articleId;
	private String userId;
	private String articleTitle;
	private String articleContent;
	private Date articleCreated;
	private String category;

	public Article(int articleId, String userId, String articleTitle, String articleContent, Date articleCreated,
			String category) {
		super();
		this.articleId = articleId;
		this.userId = userId;
		this.articleTitle = articleTitle;
		this.articleContent = articleContent;
		this.articleCreated = articleCreated;
		this.category = category;
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

	public String getArticleTitle() {
		return articleTitle;
	}

	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}

	public String getArticleContent() {
		return articleContent;
	}

	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
	}

	public Date getArticleCreated() {
		return articleCreated;
	}

	public void setArticleCreated(Date articleCreated) {
		this.articleCreated = articleCreated;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
