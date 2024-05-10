package com.ssafy.ddudu.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.ddudu.model.dto.User;
import com.ssafy.ddudu.model.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/hello")
	public List<User> hello() {
		return userService.getUsers();
	}

	@PostMapping(value = "/regist", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<?> registerUser(
	        @RequestPart("user") User user,
	        @RequestPart(value = "file", required = false) MultipartFile file,
	        HttpSession session) throws IllegalStateException, IOException {
			System.out.println(user);
			System.out.println(file.getName());

	    if (file != null && !file.isEmpty()) {
	        String uploadDirPath = session.getServletContext().getRealPath("/resources");
	        File uploadDir = new File(uploadDirPath);
	        if (!uploadDir.exists()) uploadDir.mkdirs();

	        String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
	        user.setProfile(filename);  // 파일 이름을 img 필드에 설정

	        file.transferTo(new File(uploadDir, filename));
	    }

	    int result = userService.insert(user);
	    if (result == 1) {
	        return ResponseEntity.ok("User registered successfully with image.");
	    } else {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to register user.");
	    }
	}
	
	
//	@Operation(summary="사용자 등록", description="사용자 회원가입")
//    @PostMapping("/regist")
//    public ResponseEntity<?> insert(@RequestBody  @Parameter(description="사용자 등록", required=true) User user) {
//        System.out.println(user);
//		int result = userService.insert(user);
//        return new ResponseEntity<>(result, result == 1 ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
//    }

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
