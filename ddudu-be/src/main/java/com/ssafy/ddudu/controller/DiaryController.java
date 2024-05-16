package com.ssafy.ddudu.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.ddudu.model.dto.Diary;
import com.ssafy.ddudu.model.service.DiaryService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/diary")
public class DiaryController {
	@Autowired
	private DiaryService diaryService;

	@GetMapping("/{userId}/{exerciseDate}")
	public ResponseEntity<Diary> getDiary(@PathVariable String userId,
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate exerciseDate) {
		Diary diary = diaryService.getDiary(userId, exerciseDate);
		if (diary == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(diary, HttpStatus.OK);
	}

	@PostMapping(value = "/{userId}/{exerciseDate}", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<Void> addDiary(@PathVariable String userId,
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate exerciseDate,
			@RequestPart("diary") String diaryJson, @RequestPart(value = "file", required = false) MultipartFile file)
			throws IllegalStateException, IOException {

		// JSON 문자열을 Diary 객체로 변환
		ObjectMapper objectMapper = new ObjectMapper();
		Diary diary = objectMapper.readValue(diaryJson, Diary.class);

		try {
			if (file != null && !file.isEmpty()) {
				String uploadDirPath = System.getProperty("user.home") + "/Desktop/test";
				Path uploadPath = Paths.get(uploadDirPath);
				if (!Files.exists(uploadPath)) {
					Files.createDirectories(uploadPath);
				}
				String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
				file.transferTo(new File(uploadDirPath, filename));
				diary.setDiaryPhoto(filename);
			}

			diary.setUserId(userId);
			diary.setExerciseDate(exerciseDate);

			int result = diaryService.addDiary(diary);

			if (result == 1) {
				return new ResponseEntity<>(HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (IOException e) {
			e.printStackTrace(); // 예외 스택 트레이스 출력
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{userId}/{exerciseDate}")
	public ResponseEntity<Void> updateDiary(@PathVariable String userId, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate exerciseDate,
            @RequestPart("diary") String diaryJson, @RequestPart(value = "file", required = false) MultipartFile file) throws IllegalStateException, IOException {
		 // JSON 문자열을 Diary 객체로 변환
        ObjectMapper objectMapper = new ObjectMapper();
        Diary diary = objectMapper.readValue(diaryJson, Diary.class);

        try {
            if (file != null && !file.isEmpty()) {
                String uploadDirPath = System.getProperty("user.home") + "/Desktop/test";
                Path uploadPath = Paths.get(uploadDirPath);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }
                String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
                file.transferTo(new File(uploadDirPath, filename));
                diary.setDiaryPhoto(filename);
            }

            diary.setUserId(userId);
            diary.setExerciseDate(exerciseDate);
            
            int result = diaryService.updateDiary(diary);

            if (result == 1) {
                return new ResponseEntity<>(HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (IOException e) {
            e.printStackTrace(); // 예외 스택 트레이스 출력
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}

	@DeleteMapping("/{userId}/{exerciseDate}")
	public ResponseEntity<Void> deleteDiary(@PathVariable String userId,
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate exerciseDate) {
		diaryService.deleteDiary(userId, exerciseDate);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}