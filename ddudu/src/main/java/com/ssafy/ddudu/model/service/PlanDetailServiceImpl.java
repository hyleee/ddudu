package com.ssafy.ddudu.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ddudu.model.dao.PlanDetailDao;
import com.ssafy.ddudu.model.dto.PlanDetail;

@Service
public class PlanDetailServiceImpl implements PlanDetailService{

	@Autowired
	PlanDetailDao planDetailDao;
	
	
	@Override
	public List<PlanDetail> getPlanDetailByPlanId(int planId) {
		return planDetailDao.getPlanDetailByPlanId(planId);
	}


	@Override
	public int addPlanDetail(PlanDetail planDetail) {
		return planDetailDao.addPlanDetail(planDetail);
	}


	@Override
	public int updatePlanDetail(PlanDetail planDetail) {
		return planDetailDao.update(planDetail);
	}


	@Override
	public int deletePlanDetail(int detailId) {
		return planDetailDao.delete(detailId);
	}
	

}
