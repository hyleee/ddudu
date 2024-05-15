package com.ssafy.ddudu.model.dao;

import java.util.List;

import com.ssafy.ddudu.model.dto.Reply;

public interface ReplyDao {
	List<Reply> findRepliesByCommentId(int commentId);

	int insertReply(Reply reply);

	int updateReply(Reply reply);

	int deleteReply(int replyId);

}
