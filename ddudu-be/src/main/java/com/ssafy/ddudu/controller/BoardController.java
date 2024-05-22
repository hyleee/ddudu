package com.ssafy.ddudu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ddudu.model.dto.Article;
import com.ssafy.ddudu.model.service.BoardService;

@RestController
@RequestMapping("/board")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class BoardController {
	@Autowired
	private BoardService boardService;

	@GetMapping("/{userArea}")
	public ResponseEntity<?> getArticlesByUserArea(@PathVariable String userArea) {
		try {
			List<Article> board = boardService.makeBoardByUserArea(userArea);
			if (board.isEmpty()) {
				// 게시글이 없는 경우, NOT FOUND 상태와 함께 메시지 반환
				return new ResponseEntity<>("해당 지역에 게시글이 없습니다: " + userArea, HttpStatus.NOT_FOUND);
			}
			// 게시글이 있는 경우, OK 상태와 함께 게시글 목록 반환
			return new ResponseEntity<>(board, HttpStatus.OK);
		} catch (Exception e) {
			// 예외 발생 시, INTERNAL SERVER ERROR 상태와 함께 오류 메시지 반환
			e.printStackTrace();
			return new ResponseEntity<>("해당 지역의 글을 검색하는 중 오류가 발생했습니다: " + userArea, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{userArea}/{category}")
	public ResponseEntity<?> getArticlesByUserAreaAndCategory(@PathVariable String userArea,
			@PathVariable String category) {
		try {
			List<Article> board = boardService.makeBoardByUserAreaAndCategory(userArea, category);
			if (board.isEmpty()) {
				// 게시글이 없는 경우, NOT FOUND 상태와 함께 메시지 반환
				return new ResponseEntity<>("해당 지역 및 카테고리에 게시글이 없습니다: " + userArea + ", " + category,
						HttpStatus.NOT_FOUND);
			}
			// 게시글이 있는 경우, OK 상태와 함께 게시글 목록 반환
			return new ResponseEntity<>(board, HttpStatus.OK);
		} catch (Exception e) {
			// 예외 발생 시, INTERNAL SERVER ERROR 상태와 함께 오류 메시지 반환
			e.printStackTrace();
			return new ResponseEntity<>("지역 및 카테고리에 따른 글 검색 중 오류가 발생했습니다: " + userArea + ", " + category,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping
	public ResponseEntity<?> getAllArticles() {
	    try {
	        List<Article> board = boardService.getAllArticles();
	        if (board.isEmpty()) {
	            return new ResponseEntity<>("게시글이 없습니다", HttpStatus.NOT_FOUND);
	        }
	        return new ResponseEntity<>(board, HttpStatus.OK);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return new ResponseEntity<>("게시글을 가져오는 중 오류가 발생했습니다", HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	

}
