package com.ssafy.ddudu.Interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.ssafy.ddudu.annotation.AuthRequired;
import com.ssafy.ddudu.util.JwtUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor implements HandlerInterceptor {
	
	@Autowired
	private JwtUtil jwtUtil;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		// 로그인 요청과 회원가입 요청은 토큰이 필요하지 않으므로 통과시킴
		if ("POST".equalsIgnoreCase(request.getMethod()) && 
		    ("/user/login".equals(request.getRequestURI()) || "/user/regist".equals(request.getRequestURI()))) {
			return true;
		}
		
		if (!(handler instanceof HandlerMethod)) {
			return true;
		}
		
		HandlerMethod method = (HandlerMethod) handler;
		
		// @AuthRequired가 붙어있는 메서드라면
		if (method.getMethodAnnotation(AuthRequired.class) != null) {

			// 요청 헤더에서 Authorization 헤더를 꺼내서 확인
			String accessToken = request.getHeader("Authorization");
			System.out.println("Access Token: " + accessToken);
			
			if (accessToken != null && accessToken.startsWith("Bearer ")) {
				accessToken = accessToken.substring(7); // "Bearer " 제거
				if (jwtUtil.validCheck(accessToken)) { // 유효한 토큰이라면
					System.out.println("Interceptor: valid token");
					return true; // 요청을 계속 진행
				}				
			}
			
			System.out.println("Interceptor: invalid token");
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			return false; // 요청을 중지
		}
		
		return true; // @AuthRequired가 붙어있지 않다면 그냥 통과
	}
}