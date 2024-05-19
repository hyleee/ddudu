package com.ssafy.ddudu.util;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Date;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtUtil {

	@Value("${jwt.key}")
	private String jwtKey;

	@Value("${jwt.accesstoken.expiretime}")
	private Long accessTokenExpireTime;

	@Value("${jwt.refreshtoken.expiretime}")
	private Long refreshTokenExpireTime;

	public String createAccessToken(String userId) throws UnsupportedEncodingException {
		System.out.println("jwt: ");
		System.out.println(jwtKey);

		long currentTime = System.currentTimeMillis(); // 현재시간

		JwtBuilder jwtAccessTokenBuilder = Jwts.builder();

		jwtAccessTokenBuilder.claim("userId", userId);
		jwtAccessTokenBuilder.setIssuedAt(new Date(currentTime));
		jwtAccessTokenBuilder.setExpiration(new Date(currentTime + accessTokenExpireTime * 1000));
		jwtAccessTokenBuilder.signWith(SignatureAlgorithm.HS256, jwtKey.getBytes("UTF-8"));

		return jwtAccessTokenBuilder.compact();
	}

	public String createRefreshToken(String userId) throws UnsupportedEncodingException {
		long currentTime = System.currentTimeMillis(); // 현재시간

		JwtBuilder jwtRefreshTokenBuilder = Jwts.builder();

		jwtRefreshTokenBuilder.claim("userId", userId);
		jwtRefreshTokenBuilder.setIssuedAt(new Date(currentTime));
		jwtRefreshTokenBuilder.setExpiration(new Date(currentTime + refreshTokenExpireTime * 1000));
		jwtRefreshTokenBuilder.signWith(SignatureAlgorithm.HS256, jwtKey.getBytes("UTF-8"));

		return jwtRefreshTokenBuilder.compact();
	}

	public String getUserId(String token) throws ParseException {
		// accessToken을 .을 기준으로 자른다.
		String[] chunks = token.split("\\."); // split안에는 정규표현식을 넣어야 하므로.
		// . == \\.

		Base64.Decoder decoder = Base64.getUrlDecoder();

		String payload = new String(decoder.decode(chunks[1]));
		System.out.println(payload);
		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject) parser.parse(payload);
		System.out.println(obj);
		String userId = (String) obj.get("userId");
		System.out.println(userId);
		return userId;
	}

	// 토큰 유효성 검사
	public boolean validCheck(String token) {
		try {
			Jwts.parser().setSigningKey(jwtKey.getBytes("UTF-8")).parseClaimsJws(token);
		} catch (ExpiredJwtException e) {
			System.out.println("Token expired");
			return false;
		} catch (UnsupportedJwtException e) {
			System.out.println("Unsupported JWT");
			return false;
		} catch (MalformedJwtException e) {
			System.out.println("Malformed JWT");
			return false;
		} catch (SignatureException e) {
			System.out.println("Invalid signature");
			return false;
		} catch (IllegalArgumentException e) {
			System.out.println("Illegal argument");
			return false;
		} catch (Exception e) {
			System.out.println("Invalid token");
			return false;
		}
		return true;
	}

}
