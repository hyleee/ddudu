package com.ssafy.ddudu.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ddudu.model.dao.ReplyDao;
import com.ssafy.ddudu.model.dto.Reply;

@Service
public class ReplyServiceImpl implements ReplyService {
	@Autowired
	private ReplyDao replyDao;

	@Override
	public List<Reply> getRepliesByCommentId(int commentId) {
		return replyDao.findRepliesByCommentId(commentId);
	}

	@Override
	public void addReply(Reply reply) {
		replyDao.insertReply(reply);
	}

	@Override
	public void updateReply(Reply reply) {
		replyDao.updateReply(reply);
	}

	@Override
	public void deleteReply(int replyId) {
		replyDao.deleteReply(replyId);
	}
}
