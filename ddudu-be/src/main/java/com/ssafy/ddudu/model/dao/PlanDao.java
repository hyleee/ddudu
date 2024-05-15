package com.ssafy.ddudu.model.dao;

import java.time.LocalDate;
import java.util.Date;

import com.ssafy.ddudu.model.dto.Plan;

public interface PlanDao {

	Plan findByDate(Date dateWithoutTime);

	int create(Plan plan);

	int delete(int planId);

	Plan findByDateAndUserId(LocalDate exerciseDate, String userId);

}
