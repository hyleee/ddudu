package com.ssafy.ddudu.model.dao;

import java.util.List;

import com.ssafy.ddudu.model.dto.PlanDetail;

public interface PlanDetailDao {

	List<PlanDetail> getPlanDetailByPlanId(int planId);

	int addPlanDetail(PlanDetail planDetail);

	int update(PlanDetail planDetail);

	int delete(int detailId);

}
