package com.ssafy.ddudu.model.dao;

import java.time.LocalDate;


import com.ssafy.ddudu.model.dto.Diary;

public interface DiaryDao {

	Diary findDiaryByUserIdAndDate(String userId, LocalDate exerciseDate);

	int insertDiary(Diary diary);

	int updateDiary(Diary diary);

	void deleteDiary(String userId, LocalDate exerciseDate);

}
