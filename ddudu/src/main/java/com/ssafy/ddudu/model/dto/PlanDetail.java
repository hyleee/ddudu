package com.ssafy.ddudu.model.dto;

public class PlanDetail {

	private int detailId;
	private int exerciseKg;
	private int exerciseCount;
	private int planId;

	public PlanDetail() {
	}

	public PlanDetail(int detailId, int exerciseKg, int exerciseCount, int planId) {
		super();
		this.detailId = detailId;
		this.exerciseKg = exerciseKg;
		this.exerciseCount = exerciseCount;
		this.planId = planId;
	}

	public int getDetailId() {
		return detailId;
	}

	public void setDetailId(int detailId) {
		this.detailId = detailId;
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

	public int getPlanId() {
		return planId;
	}

	public void setPlanId(int planId) {
		this.planId = planId;
	}

	@Override
	public String toString() {
		return "PlanDetail [detailId=" + detailId + ", exerciseKg=" + exerciseKg + ", exerciseCount=" + exerciseCount
				+ ", planId=" + planId + "]";
	}

	
	

}
