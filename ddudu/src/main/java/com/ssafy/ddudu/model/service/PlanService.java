package com.ssafy.ddudu.model.service;

import java.time.LocalDate;
import java.util.Date;

import com.ssafy.ddudu.model.dto.Plan;

public interface PlanService {

//	Plan getPlanByDateAndUserId(Date dateWithoutTime);

	int createPlan(Plan newPlan);

	int deletePlan(int planId);

	Plan getPlanByDateAndUserId(LocalDate exerciseDate, String userId);


}
