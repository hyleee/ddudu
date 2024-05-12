package com.ssafy.ddudu.controller;

import com.ssafy.ddudu.model.dto.Article;
import com.ssafy.ddudu.model.service.ArticleService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
@Tag(name = "ArticleController", description = "게시글 관리")
public class ArticleController {

	@Autowired
	private ArticleService articleService;

	@PostMapping
	@Operation(summary = "게시글 생성", description = "새로운 게시글을 생성합니다.")
	public ResponseEntity<Void> createArticle(@RequestBody Article article) {
		articleService.createArticle(article);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/{articleId}")
	@Operation(summary = "게시글 조회", description = "특정 게시글의 정보를 조회합니다.")
	public ResponseEntity<Article> getArticleById(@PathVariable int articleId) {
		Article article = articleService.getArticleById(articleId);
		return ResponseEntity.ok(article);
	}

	@PutMapping("/{articleId}")
	@Operation(summary = "게시글 수정", description = "특정 게시글의 정보를 수정합니다.")
	public ResponseEntity<Void> updateArticle(@PathVariable int articleId, @RequestBody Article article) {
		article.setArticleId(articleId);
		articleService.updateArticle(article);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/{articleId}")
	@Operation(summary = "게시글 삭제", description = "특정 게시글을 삭제합니다.")
	public ResponseEntity<Void> deleteArticle(@PathVariable int articleId) {
		articleService.deleteArticle(articleId);
		return ResponseEntity.ok().build();
	}
}
