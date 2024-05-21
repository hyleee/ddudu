package com.ssafy.ddudu.model.service;

import com.ssafy.ddudu.model.dto.Article;

public interface ArticleService {
	
	void createArticle(Article article);
    Article getArticleById(int articleId);
    void updateArticle(Article article);
    void deleteArticle(int articleId);
	int countArticlesByUserId(String userId);

}
