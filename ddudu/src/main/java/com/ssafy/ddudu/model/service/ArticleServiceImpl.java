package com.ssafy.ddudu.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.ddudu.model.dao.ArticleDao;
import com.ssafy.ddudu.model.dto.Article;

@Service
public class ArticleServiceImpl implements ArticleService {
	@Autowired
	private ArticleDao articleDao;

	@Override
	@Transactional
	public void createArticle(Article article) {
		articleDao.createArticle(article);
	}

	@Override
	@Transactional(readOnly = true)
	public Article getArticleById(int articleId) {
		return articleDao.getArticleById(articleId);
	}

	@Override
	@Transactional
	public void updateArticle(Article article) {
		articleDao.updateArticle(article);
	}

	@Override
	@Transactional
	public void deleteArticle(int articleId) {
		articleDao.deleteArticle(articleId);
	}
}
