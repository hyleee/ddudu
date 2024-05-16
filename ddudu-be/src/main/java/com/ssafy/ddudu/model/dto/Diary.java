package com.ssafy.ddudu.model.dto;

import java.time.LocalDate;

public class Diary {
	private String userId;
	private String diaryContent;
	private int todayWeight;
	private String diaryPhoto;
	private LocalDate exerciseDate;

	Diary() {
	}

	public Diary(String userId, String diaryContent, int todayWeight, String diaryPhoto,LocalDate exerciseDate) {
		super();
		this.userId = userId;
		this.diaryContent = diaryContent;
		this.todayWeight = todayWeight;
		this.diaryPhoto = diaryPhoto;
		this.exerciseDate = exerciseDate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDiaryContent() {
		return diaryContent;
	}

	public void setDiaryContent(String diaryContent) {
		this.diaryContent = diaryContent;
	}

	public int getTodayWeight() {
		return todayWeight;
	}

	public void setTodayWeight(int todayWeight) {
		this.todayWeight = todayWeight;
	}

	public String getDiaryPhoto() {
		return diaryPhoto;
	}

	public void setDiaryPhoto(String diaryPhoto) {
		this.diaryPhoto = diaryPhoto;
	}

	public LocalDate getExerciseDate() {
		return exerciseDate;
	}

	public void setExerciseDate(LocalDate exerciseDate) {
		this.exerciseDate = exerciseDate;
	}

}
