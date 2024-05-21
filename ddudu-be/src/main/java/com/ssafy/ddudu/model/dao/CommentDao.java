package com.ssafy.ddudu.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssafy.ddudu.model.dto.Comment;

public interface CommentDao {
	int insertComment(Comment comment);

	List<Comment> selectCommentsByArticleId(int articleId);

	void updateComment(Comment comment);

	void deleteComment(int commentId, int articleId);
	
	int countCommentsByUserId(@Param("userId") String userId);
}
