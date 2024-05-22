package com.ssafy.ddudu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ddudu.model.dto.Rank;
import com.ssafy.ddudu.model.service.RankService;

@RestController
@RequestMapping("/rank")
//@CrossOrigin(origins = "*")
public class RankController {

    @Autowired
    private RankService rankService;

    @GetMapping("/chest")
    public ResponseEntity<?> getChestRank() {
        try {
            List<Rank> ranks = rankService.getChestRank();
            return new ResponseEntity<>(ranks, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error retrieving chest rank", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/back")
    public ResponseEntity<?> getBackRank() {
        try {
            List<Rank> ranks = rankService.getBackRank();
            return new ResponseEntity<>(ranks, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error retrieving back rank", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/shoulders")
    public ResponseEntity<?> getShouldersRank() {
        try {
            List<Rank> ranks = rankService.getShouldersRank();
            return new ResponseEntity<>(ranks, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error retrieving shoulders rank", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/legs")
    public ResponseEntity<?> getLegsRank() {
        try {
            List<Rank> ranks = rankService.getLegsRank();
            return new ResponseEntity<>(ranks, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error retrieving legs rank", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/arms")
    public ResponseEntity<?> getArmsRank() {
        try {
            List<Rank> ranks = rankService.getArmsRank();
            return new ResponseEntity<>(ranks, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error retrieving arms rank", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/core")
    public ResponseEntity<?> getCoreRank() {
        try {
            List<Rank> ranks = rankService.getCoreRank();
            return new ResponseEntity<>(ranks, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error retrieving core rank", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
