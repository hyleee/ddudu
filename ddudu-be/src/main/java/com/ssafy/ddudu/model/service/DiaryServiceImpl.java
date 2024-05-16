package com.ssafy.ddudu.model.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ddudu.model.dao.DiaryDao;
import com.ssafy.ddudu.model.dto.Diary;

@Service
public class DiaryServiceImpl implements DiaryService {
	@Autowired
	private DiaryDao diaryDao;

	@Override
	public Diary getDiary(String userId, LocalDate exerciseDate) {
		return diaryDao.findDiaryByUserIdAndDate(userId, exerciseDate);
	}

	@Override
	public int addDiary(Diary diary) {
		try {
			// 데이터베이스에 Diary 저장 로직
			return diaryDao.insertDiary(diary);
		} catch (Exception e) {
			// 로깅 및 예외 처리
			throw new RuntimeException("Diary 저장 중 오류 발생", e);
		}
	}

	@Override
	public int updateDiary(Diary diary) {
		return diaryDao.updateDiary(diary);
	}

	@Override
	public void deleteDiary(String userId, LocalDate exerciseDate) {
		diaryDao.deleteDiary(userId, exerciseDate);
	}
}
