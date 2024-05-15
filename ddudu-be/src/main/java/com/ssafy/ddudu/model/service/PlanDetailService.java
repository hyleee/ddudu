package com.ssafy.ddudu.model.service;

import java.util.List;

import com.ssafy.ddudu.model.dto.PlanDetail;

public interface PlanDetailService {

	List<PlanDetail> getPlanDetailByPlanId(int planId);

	int addPlanDetail(PlanDetail planDetail);

	int updatePlanDetail(PlanDetail planDetail);

	int deletePlanDetail(int detailId);

}
