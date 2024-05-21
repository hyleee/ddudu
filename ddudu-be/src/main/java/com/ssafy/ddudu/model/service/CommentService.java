package com.ssafy.ddudu.model.service;

import java.util.List;

import com.ssafy.ddudu.model.dto.Comment;

public interface CommentService {
	int addComment(Comment comment);

	List<Comment> getCommentsByArticleId(int articleId);

	void modifyComment(Comment comment);

	void removeComment(int commentId, int articleId);

	int countCommentsByUserId(String userId);
}
