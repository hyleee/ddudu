package com.ssafy.ddudu.model.dto;

import java.time.LocalDate;
import java.util.Date;

public class Plan {

	private int planId;
	private LocalDate  exerciseDate;
	private String userId;
	private String bodyPart;
	private String exerciseName;

	public Plan() {
		super();
	}

	public Plan(int planId, LocalDate  exerciseDate, String userId, String bodyPart, String exerciseName) {
		super();
		this.planId = planId;
		this.exerciseDate = exerciseDate;
		this.userId = userId;
		this.bodyPart = bodyPart;
		this.exerciseName = exerciseName;
	}

	public int getPlanId() {
		return planId;
	}

	public void setPlanId(int planId) {
		this.planId = planId;
	}

	public LocalDate  getExerciseDate() {
		return exerciseDate;
	}

	public void setExerciseDate(LocalDate  exerciseDate) {
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

	@Override
	public String toString() {
		return "Plan [planId=" + planId + ", exerciseDate=" + exerciseDate + ", userId=" + userId + ", bodyPart="
				+ bodyPart + ", exerciseName=" + exerciseName + "]";
	}

	
}
