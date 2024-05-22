package com.ssafy.ddudu.controller;

import com.ssafy.ddudu.model.dto.Article;
import com.ssafy.ddudu.model.service.ArticleService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
@Tag(name = "ArticleController", description = "게시글 관리")
@CrossOrigin(origins = "*")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping
    @Operation(summary = "게시글 생성", description = "새로운 게시글을 생성합니다.")
    public ResponseEntity<?> createArticle(@RequestBody Article article) {
        try {
            articleService.createArticle(article);
            return new ResponseEntity<>("게시글이 성공적으로 생성되었습니다.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("게시글 생성 중 오류가 발생했습니다.", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{articleId}")
    @Operation(summary = "게시글 조회", description = "특정 게시글의 정보를 조회합니다.")
    public ResponseEntity<?> getArticleById(@PathVariable int articleId) {
        Article article = articleService.getArticleById(articleId);
        return article != null ? new ResponseEntity<>(article, HttpStatus.OK) :
                                 new ResponseEntity<>("게시글을 찾을 수 없습니다.", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{articleId}")
    @Operation(summary = "게시글 수정", description = "특정 게시글의 정보를 수정합니다.")
    public ResponseEntity<?> updateArticle(@PathVariable int articleId, @RequestBody Article article) {
        try {
            article.setArticleId(articleId);
            articleService.updateArticle(article);
            return new ResponseEntity<>("게시글이 성공적으로 수정되었습니다.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("게시글 수정 중 오류가 발생했습니다.", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{articleId}")
    @Operation(summary = "게시글 삭제", description = "특정 게시글을 삭제합니다.")
    public ResponseEntity<?> deleteArticle(@PathVariable int articleId) {
        try {
            articleService.deleteArticle(articleId);
            return new ResponseEntity<>("게시글이 성공적으로 삭제되었습니다.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("게시글 삭제 중 오류가 발생했습니다.", HttpStatus.BAD_REQUEST);
        }
    }
}
