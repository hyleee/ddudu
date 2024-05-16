package com.ssafy.ddudu.model.service;

import java.time.LocalDate;

import com.ssafy.ddudu.model.dto.Diary;

public interface DiaryService {

	Diary getDiary(String userId, LocalDate exerciseDate);

	int addDiary(Diary diary);

	int updateDiary(Diary diary);

	void deleteDiary(String userId, LocalDate exerciseDate);

}
