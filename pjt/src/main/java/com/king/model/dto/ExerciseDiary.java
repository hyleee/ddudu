package com.king.model.dto;

import java.util.Date;

public class ExerciseDiary {

	private String userId;
	private String diaryContent;
	private int todayWeight;
	private byte[] diaryPhoto;
	private Date exerciseDate;
	public ExerciseDiary(String userId, String diaryContent, int todayWeight, byte[] diaryPhoto, Date exerciseDate) {
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
	public byte[] getDiaryPhoto() {
		return diaryPhoto;
	}
	public void setDiaryPhoto(byte[] diaryPhoto) {
		this.diaryPhoto = diaryPhoto;
	}
	public Date getExerciseDate() {
		return exerciseDate;
	}
	public void setExerciseDate(Date exerciseDate) {
		this.exerciseDate = exerciseDate;
	}
	
	

}
