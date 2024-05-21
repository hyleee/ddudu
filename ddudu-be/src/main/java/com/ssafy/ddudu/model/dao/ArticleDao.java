package com.ssafy.ddudu.model.dao;

import org.apache.ibatis.annotations.Param;

import com.ssafy.ddudu.model.dto.Article;

public interface ArticleDao {
	
	void createArticle(Article article);
    Article getArticleById(int articleId);
    void updateArticle(Article article);
    void deleteArticle(int articleId);

    int countArticlesByUserId(@Param("userId") String userId);
}
