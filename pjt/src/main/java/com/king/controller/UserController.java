package com.king.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.king.model.dao.UserDao;
import com.king.model.dto.User;
import com.king.model.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Operation(summary="사용자 등록", description="사용자 회원가입")
	@PostMapping("/regist")
	public ResponseEntity<?> insert(@RequestBody @Parameter(description="사용자 등록", required=true) User user) {
		int result = userService.insert(user);
		return new ResponseEntity<>(result, result == 1 ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}
	
	@Operation(summary="로그인", description="사용자 로그인")
	@PostMapping("/login")
	public ResponseEntity<String> login(
			@Parameter(description = "사용자명", required = true) @RequestParam String userId,
			@Parameter(description = "비밀번호", required = true) @RequestParam String password) {
		
		boolean isAuthenticated = userService.login(userId, password);
		if(isAuthenticated) {
			return new ResponseEntity<>("Login successful", HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
		}
	}
	
	




}
