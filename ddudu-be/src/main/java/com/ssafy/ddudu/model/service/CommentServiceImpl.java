package com.ssafy.ddudu.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.ddudu.model.dao.CommentDao;
import com.ssafy.ddudu.model.dto.Comment;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentDao commentDao;


	@Transactional
	public int addComment(Comment comment) {
		return commentDao.insertComment(comment);
	}

	@Transactional(readOnly = true)
	public List<Comment> getCommentsByArticleId(int articleId) {
		return commentDao.selectCommentsByArticleId(articleId);
	}

	@Transactional
	public void modifyComment(Comment comment) {
		commentDao.updateComment(comment);
	}

	@Transactional
	public void removeComment(int commentId, int articleId) {
		commentDao.deleteComment(commentId, articleId);
	}
}
