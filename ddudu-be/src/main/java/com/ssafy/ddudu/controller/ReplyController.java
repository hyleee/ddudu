package com.ssafy.ddudu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ddudu.model.dto.Reply;
import com.ssafy.ddudu.model.service.ReplyService;

@RestController
@RequestMapping("/reply")
//@CrossOrigin(origins="*")
public class ReplyController {

	@Autowired
	private ReplyService replyService;

	@GetMapping("/{commentId}")
	public ResponseEntity<List<Reply>> getRepliesByCommentId(@PathVariable int commentId) {
		List<Reply> replies = replyService.getRepliesByCommentId(commentId);
		return new ResponseEntity<>(replies, HttpStatus.OK);
	}

	@PostMapping("/{commentId}")
	public ResponseEntity<Void> createReply(@PathVariable int commentId, @RequestBody Reply reply) {
		reply.setCommentId(commentId);
		replyService.addReply(reply);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PutMapping("/{replyId}")
	public ResponseEntity<Void> updateReply(@PathVariable int replyId, @RequestBody Reply reply) {
		reply.setReplyId(replyId);
		replyService.updateReply(reply);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/{replyId}")
	public ResponseEntity<Void> deleteReply(@PathVariable int replyId) {
		replyService.deleteReply(replyId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
