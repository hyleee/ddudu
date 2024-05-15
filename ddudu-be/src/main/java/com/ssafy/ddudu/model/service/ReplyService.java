package com.ssafy.ddudu.model.service;

import java.util.List;

import com.ssafy.ddudu.model.dto.Reply;

public interface ReplyService {
	List<Reply> getRepliesByCommentId(int commentId);

	void addReply(Reply reply);

	void updateReply(Reply reply);

	void deleteReply(int replyId);
}
