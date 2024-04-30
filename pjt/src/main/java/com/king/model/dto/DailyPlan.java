package com.king.model.dto;

import java.util.Date;

public class DailyPlan {

	private Date exerciseDate;
	private String userId;
	private String bodyPart;
	private String exerciseName;
	private int exerciseKg;
	private int exerciseCount;

	public DailyPlan(Date exerciseDate, String userId, String bodyPart, String exerciseName, int exerciseKg,
			int exerciseCount) {
		super();
		this.exerciseDate = exerciseDate;
		this.userId = userId;
		this.bodyPart = bodyPart;
		this.exerciseName = exerciseName;
		this.exerciseKg = exerciseKg;
		this.exerciseCount = exerciseCount;
	}

	public Date getExerciseDate() {
		return exerciseDate;
	}

	public void setExerciseDate(Date exerciseDate) {
		this.exerciseDate = exerciseDate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getBodyPart() {
		return bodyPart;
	}

	public void setBodyPart(String bodyPart) {
		this.bodyPart = bodyPart;
	}

	public String getExerciseName() {
		return exerciseName;
	}

	public void setExerciseName(String exerciseName) {
		this.exerciseName = exerciseName;
	}

	public int getExerciseKg() {
		return exerciseKg;
	}

	public void setExerciseKg(int exerciseKg) {
		this.exerciseKg = exerciseKg;
	}

	public int getExerciseCount() {
		return exerciseCount;
	}

	public void setExerciseCount(int exerciseCount) {
		this.exerciseCount = exerciseCount;
	}

}
