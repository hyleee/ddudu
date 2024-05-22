package com.ssafy.ddudu.controller;


import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ssafy.ddudu.annotation.AuthRequired;
import com.ssafy.ddudu.model.dto.Auth;
import com.ssafy.ddudu.model.dto.User;
import com.ssafy.ddudu.model.service.AuthService;
import com.ssafy.ddudu.model.service.UserService;
import com.ssafy.ddudu.util.JwtUtil;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth")
//@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthService authService;
    
    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Value("${jwt.refreshtoken.expiretime}")
    private int refreshTokenExpireTime;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user, HttpServletResponse response) throws UnsupportedEncodingException {
        Map<String, Object> result = new HashMap<>();
        // DB에서 유저 가져오기
        User dbUser = userService.getUserById(user.getUserId());

        // 일치하는 유저가 없다면 UNAUTHORIZED 반환.
        if (dbUser == null) {
            result.put("message", "일치하는 유저가 없습니다.");
            return new ResponseEntity<>(result, HttpStatus.UNAUTHORIZED);
        }

        // 있으면 Token 발급
        // AccessToken, RefreshToken 두 개를 발급해준다.
        String accessToken = jwtUtil.createAccessToken(dbUser.getUserId());
        String refreshToken = jwtUtil.createRefreshToken(dbUser.getUserId());

        // refreshtoken은 처음 발급할 때 -> DB에 저장.
        authService.saveRefreshToken(dbUser.getUserId(), refreshToken);

        // refreshToken을 쿠키에 저장.
        Cookie cookie = new Cookie("refreshToken", refreshToken);
        cookie.setMaxAge(refreshTokenExpireTime);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);

        result.put("accessToken", accessToken);
        result.put("name", dbUser.getUserName());

        return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
    }

    @AuthRequired // 토큰이 있어야지만 접근 가능한 메서드.
    @GetMapping("/mypage")
    public ResponseEntity<?> mypage(HttpServletRequest request) throws ParseException {
        String accessToken = request.getHeader("Authorization");
        String userId = jwtUtil.getUserId(accessToken);
        return new ResponseEntity<>(userId, HttpStatus.OK);
    }

    @GetMapping("/refresh")
    public ResponseEntity<?> refreshToken(HttpServletRequest request) throws ParseException, UnsupportedEncodingException {
        // COOKIE 헤더에서 refreshToken 가져오기
        Cookie[] cookies = request.getCookies();
        String refreshToken = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("refreshToken")) {
                refreshToken = cookie.getValue();
            }
        }
        if (refreshToken != null && jwtUtil.validCheck(refreshToken)) {
            String userId = jwtUtil.getUserId(refreshToken);
            Auth storedAuth = authService.findRefreshToken(userId);

            if (storedAuth != null && storedAuth.getRefreshToken().equals(refreshToken)) {
                String newAccessToken = jwtUtil.createAccessToken(userId);
                return new ResponseEntity<>(newAccessToken, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Invalid refresh token", HttpStatus.UNAUTHORIZED);
            }
        } else {
            return new ResponseEntity<>("Invalid refresh token", HttpStatus.UNAUTHORIZED);
        }
    }
}
