package com.ssafy.ddudu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ddudu.model.dto.Comment;
import com.ssafy.ddudu.model.service.CommentService;

import io.swagger.v3.oas.annotations.Operation;
@RestController
@RequestMapping("/comment")
public class CommentController {

	@Autowired
	private CommentService commentService;

	@PostMapping("/{articleId}")
	@Operation(summary = "댓글 생성", description = "새로운 댓글을 생성합니다.")
	public ResponseEntity<?> addComment(@RequestBody Comment comment, @PathVariable int articleId) {
		try {
			comment.setArticleId(articleId);
			int result = commentService.addComment(comment);
			System.out.println(comment.toString());
			return new ResponseEntity<>(result, result == 1 ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>("댓글 추가 중 오류가 발생했습니다.", HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/{articleId}")
	@Operation(summary = "댓글 조회", description = "특정 게시글의 댓글 목록을 조회합니다.")
	public ResponseEntity<?> getCommentsByArticleId(@PathVariable int articleId) {
		try {
			List<Comment> comments = commentService.getCommentsByArticleId(articleId);
			if (comments.isEmpty()) {
				return new ResponseEntity<>("해당 게시글에 댓글이 없습니다.", HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(comments, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("댓글 조회 중 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{articleId}/{commentId}")
	@Operation(summary = "댓글 수정", description = "특정 댓글의 내용을 수정합니다.")
	public ResponseEntity<?> updateComment(@PathVariable int articleId, @PathVariable int commentId,
			@RequestBody Comment comment) {
		try {
			comment.setArticleId(articleId);
			comment.setCommentId(commentId);
			commentService.modifyComment(comment);
			return new ResponseEntity<>("댓글이 성공적으로 수정되었습니다.", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("댓글 수정 중 오류가 발생했습니다.", HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/{articleId}/{commentId}")
	@Operation(summary = "댓글 삭제", description = "특정 댓글을 삭제합니다.")
	public ResponseEntity<?> deleteComment(@PathVariable int articleId, @PathVariable int commentId) {
		try {
			commentService.removeComment(commentId, articleId);
			return new ResponseEntity<>("댓글이 성공적으로 삭제되었습니다.", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("댓글 삭제 중 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
