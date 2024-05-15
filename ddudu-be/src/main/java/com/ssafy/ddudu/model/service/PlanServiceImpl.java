package com.ssafy.ddudu.model.service;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ddudu.model.dao.PlanDao;
import com.ssafy.ddudu.model.dto.Plan;

@Service
public class PlanServiceImpl implements PlanService{

	@Autowired
	PlanDao planDao;
	

	@Override
	public int createPlan(Plan plan) {
		return planDao.create(plan);
	}

	@Override
	public int deletePlan(int planId) {
		return planDao.delete(planId);
	}

	@Override
	public Plan getPlanByDateAndUserId(LocalDate exerciseDate, String userId) {
		return planDao.findByDateAndUserId(exerciseDate, userId);
	}


}
