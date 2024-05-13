package com.ssafy.ddudu.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ddudu.model.dao.BoardDao;
import com.ssafy.ddudu.model.dto.Article;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDao boardDao;

	@Override
	public List<Article> makeBoardByUserArea(String userArea) {
		return boardDao.findArticlesByUserArea(userArea);
	}

	@Override
	public List<Article> makeBoardByUserAreaAndCategory(String userArea, String category) {
		return boardDao.findArticlesByUserAreaAndCategory(userArea, category);
	}

}
