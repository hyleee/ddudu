package com.ssafy.ddudu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ddudu.model.service.FollowService;

@RestController
@RequestMapping("/follow")
public class FollowController {
    @Autowired
    private FollowService followService;

    @GetMapping("/{userId}/followers")
    public ResponseEntity<?> getFollowers(@PathVariable String userId) {
        try {
            List<String> followers = followService.getFollowers(userId);
            if (followers.isEmpty()) {
                return new ResponseEntity<>("팔로워 0.", HttpStatus.OK);
            }
            return new ResponseEntity<>(followers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("팔로워 목록을 불러오는 중 에러가 발생했습니다: " + userId, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{userId}/followings")
    public ResponseEntity<?> getFollowings(@PathVariable String userId) {
        try {
            List<String> followings = followService.getFollowings(userId);
            if (followings.isEmpty()) {
                return new ResponseEntity<>("팔로잉 0", HttpStatus.OK);
            }
            return new ResponseEntity<>(followings, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("팔로잉 목록을 불러오는 중 에러가 발생했습니다: " + userId, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/{followerId}/{followingId}")
    public ResponseEntity<?> followUser(@PathVariable String followerId, @PathVariable String followingId) {
        try {
            followService.followUser(followerId, followingId);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("팔로우 신청 중 에러가 발생했습니다: " + followerId + ", " + followingId, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{followerId}/{followingId}")
    public ResponseEntity<?> unfollowUser(@PathVariable String followerId, @PathVariable String followingId) {
        try {
            followService.unfollowUser(followerId, followingId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("팔로우 해제 중 에러가 발생했습니다: " + followerId + ", " + followingId, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
