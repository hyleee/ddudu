package com.ssafy.ddudu.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.ddudu.model.dto.User;
import com.ssafy.ddudu.model.service.ArticleService;
import com.ssafy.ddudu.model.service.CommentService;
import com.ssafy.ddudu.model.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
//@CrossOrigin(origins = "*")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private CommentService commentService;
	
	@Autowired 
    private ArticleService articleService;

	@Autowired
	ResourceLoader resLoader;

	@Operation(summary = "사용자 조회", description = "사용자 정보를 조회합니다.")
	@GetMapping("/{id}")
	public ResponseEntity<?> userDetail(@PathVariable("id") String id) {
		User user = userService.getUserById(id);
		System.out.println(user.toString());
		return new ResponseEntity<>(user, user == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
	}
	
	@Operation(summary = "사용자 이름으로 검색", description = "이름이 일치하는 사용자 목록을 조회합니다.")
	@GetMapping("/search/{userName}")
    public List<User> searchUsersByName(@PathVariable String userName) {
        return userService.findByUserName(userName);
    }

	@PostMapping(value = "/regist", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<?> userRegist(@RequestPart("user") User user,
	        @RequestPart(value = "file", required = false) MultipartFile file, HttpSession session)
	        throws IllegalStateException, IOException {
	    int result = userService.insert(user, file);
	    if (result == 1) {
	        return ResponseEntity.ok("User registered successfully with image.");
	    } else {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to register user.");
	    }
	}

	
	
//	@PostMapping(value = "/regist", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
//	public ResponseEntity<?> userRegist(@RequestPart("user") User user,
//			@RequestPart(value = "file", required = false) MultipartFile file, HttpSession session)
//			throws IllegalStateException, IOException {
//		System.out.println(user);
//		if (file != null && !file.isEmpty()) {
//			String userHome = System.getProperty("user.home");
//			String uploadDirPath = userHome + "/Desktop/test";
//			Path uploadPath = Paths.get(uploadDirPath);
//			if (!Files.exists(uploadPath)) {
//				Files.createDirectories(uploadPath);
//			}
//			String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
//			file.transferTo(new File(uploadDirPath, filename));
//			user.setUserProfile(filename); // 파일 이름을 img 필드에 설정
//		}
//
//		int result = userService.insert(user, file);
//		if (result == 1) {
//			return ResponseEntity.ok("User registered successfully with image.");
//		} else {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to register user.");
//		}
//	}

	@Operation(summary = "로그인", description = "사용자 로그인")
	@PostMapping("/login")
	public ResponseEntity<String> login(@Parameter(description = "사용자명", required = true) @RequestParam String userId,
			@Parameter(description = "비밀번호", required = true) @RequestParam String password) {

		boolean isAuthenticated = userService.login(userId, password);
		if (isAuthenticated) {
			return new ResponseEntity<>("Login successful", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
		}
	}

	@Operation(summary = "로그아웃", description = "사용자 로그아웃")
	@GetMapping("/logout")
	public ResponseEntity<String> logout(HttpSession session) {
		session.invalidate();
		return new ResponseEntity<>("Logout succesful", HttpStatus.OK);
	}

	@Operation(summary = "수정", description = "사용자 정보 수정")
	@PutMapping("/update/{userId}")
	public ResponseEntity<?> update(@PathVariable("userId") String userId,
			@Parameter(description = "사용자 정보", required = true) User user) {
		user.setUserId(userId);
		int result = userService.update(user);
		return new ResponseEntity<>(result, result == 1 ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}

	@Operation(summary = "삭제", description = "사용자 정보 삭제")
	@DeleteMapping("{userId}")
	public ResponseEntity<?> delete(@PathVariable("userId") String userId) {
		int result = userService.delete(userId);
		return new ResponseEntity<>(result, result == 1 ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}
	
	// 유저의 댓글 수를 가져오는 엔드포인트
    @GetMapping("/{userId}/commentCount")
    public int getUserCommentCount(@PathVariable("userId") String userId) {
    	System.out.println("여기");
        return commentService.countCommentsByUserId(userId);
    }
    
    // 유저의 게시물 수를 가져오는 엔드포인트
    @GetMapping("/{userId}/articleCount")
    public int getUserArticleCount(@PathVariable("userId") String userId) {
        return articleService.countArticlesByUserId(userId);
    }
    
 // 프로필 이미지 업로드 엔드포인트
    @PostMapping("/uploadProfilePicture")
    public ResponseEntity<String> uploadProfilePicture(@RequestParam("file") MultipartFile file, @RequestParam("userId") String userId) throws IOException {
        String uploadDirPath = System.getProperty("user.home") + "/Desktop/test";
        Path uploadPath = Paths.get(uploadDirPath);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        File dest = new File(uploadDirPath, filename);
        try {
            file.transferTo(dest);
            String fileUrl = "http://localhost:8080/files/" + filename;
            return ResponseEntity.ok(fileUrl);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File upload failed");
        }
    }

    // 프로필 이미지를 제공하는 엔드포인트
    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<?> getFile(@PathVariable String filename) {
        String userHome = System.getProperty("user.home");
        Path filePath = Paths.get(userHome + "/Desktop/test/" + filename);
        if (Files.exists(filePath)) {
            try {
                String contentType = Files.probeContentType(filePath);
                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(contentType))
                        .body(Files.readAllBytes(filePath));
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error reading file");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File not found");
        }
    }


}
