package com.ssafy.ddudu.model.dao;

import java.util.List;

import com.ssafy.ddudu.model.dto.Article;

public interface BoardDao {

	List<Article> findArticlesByUserArea(String userArea);

	List<Article> findArticlesByUserAreaAndCategory(String userArea, String category);

	List<Article> findAllArticles();

}
