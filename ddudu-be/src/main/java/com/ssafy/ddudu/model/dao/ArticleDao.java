package com.ssafy.ddudu.model.dao;

import com.ssafy.ddudu.model.dto.Article;

public interface ArticleDao {
	
	void createArticle(Article article);
    Article getArticleById(int articleId);
    void updateArticle(Article article);
    void deleteArticle(int articleId);

}
