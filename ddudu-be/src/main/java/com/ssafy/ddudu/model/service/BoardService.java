package com.ssafy.ddudu.model.service;

import java.util.List;

import com.ssafy.ddudu.model.dto.Article;

public interface BoardService {
	List<Article> makeBoardByUserArea(String userArea);

	List<Article> makeBoardByUserAreaAndCategory(String userArea, String category);

	List<Article> getAllArticles();

}
