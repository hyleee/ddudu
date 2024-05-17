package com.ssafy.ddudu.model.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.ssafy.ddudu.model.dto.Plan;

public interface PlanService {

	int createPlan(Plan newPlan);

	int deletePlan(int planId);

	List<Plan> getPlanByDateAndUserId(LocalDate exerciseDate, String userId);

}
