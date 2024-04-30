package com.king.model.dto;

import java.util.Date;

public class ExercisePerPartSum {

	private Date exerciseDate;
	private String userId;
	private int chestKg;
	private int backKg;
	private int shoulderKg;
	private int legKg;
	private int absKg;
	private int armKg;

	public ExercisePerPartSum(Date exerciseDate, String userId, int chestKg, int backKg, int shoulderKg, int legKg,
			int absKg, int armKg) {
		super();
		this.exerciseDate = exerciseDate;
		this.userId = userId;
		this.chestKg = chestKg;
		this.backKg = backKg;
		this.shoulderKg = shoulderKg;
		this.legKg = legKg;
		this.absKg = absKg;
		this.armKg = armKg;
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

	public int getChestKg() {
		return chestKg;
	}

	public void setChestKg(int chestKg) {
		this.chestKg = chestKg;
	}

	public int getBackKg() {
		return backKg;
	}

	public void setBackKg(int backKg) {
		this.backKg = backKg;
	}

	public int getShoulderKg() {
		return shoulderKg;
	}

	public void setShoulderKg(int shoulderKg) {
		this.shoulderKg = shoulderKg;
	}

	public int getLegKg() {
		return legKg;
	}

	public void setLegKg(int legKg) {
		this.legKg = legKg;
	}

	public int getAbsKg() {
		return absKg;
	}

	public void setAbsKg(int absKg) {
		this.absKg = absKg;
	}

	public int getArmKg() {
		return armKg;
	}

	public void setArmKg(int armKg) {
		this.armKg = armKg;
	}

}
