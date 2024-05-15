package com.ssafy.ddudu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ddudu.model.dto.PlanDetail;
import com.ssafy.ddudu.model.service.PlanDetailService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/plan/detail/{planId}")
@CrossOrigin(origins="*")
public class PlanDetailController {

	@Autowired
	PlanDetailService planDetailService;
	
	
	@Operation(summary = "해당 운동 상세 조회", description = "해당 운동 상세 정보를 조회합니다.")
	@GetMapping("")
	public ResponseEntity<List<PlanDetail>> getPlanDetailByPlanId(@PathVariable("planId") int planId) {
	    List<PlanDetail> planDetails = planDetailService.getPlanDetailByPlanId(planId);
	    if(planDetails != null && !planDetails.isEmpty()) {
	        return new ResponseEntity<>(planDetails, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	
	
	@Operation(summary = "해당 운동 상세 추가", description = "해당 운동 상세 정보를 추가합니다.")
	@PostMapping("")
	public ResponseEntity<?> addPlanDetail(@PathVariable("planId") int planId, @RequestBody PlanDetail planDetail) {
		planDetail.setPlanId(planId);
		int result = planDetailService.addPlanDetail(planDetail);
		System.out.println(planDetail);
		return new ResponseEntity<>(result, result == 1 ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}
	
	
	@Operation(summary = "해당 운동 상세 수정", description = "해당 운동 상세 정보를 수정합니다.")
	@PutMapping("/{detailId}")
	public ResponseEntity<?> updatePlanDetail(@PathVariable("detailId") int detailId, @Parameter(description = "운동 상세 정보", required = true) PlanDetail planDetail) {
		planDetail.setDetailId(detailId);
		int result = planDetailService.updatePlanDetail(planDetail);
		return new ResponseEntity<>(result, result == 1 ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}
	
	
	@Operation(summary = "해당 운동 상세 삭제", description = "해당 운동 상세 정보를 삭제합니다.")
	@DeleteMapping("/{detailId}")
	public ResponseEntity<?> deletePlanDetail(@PathVariable("detailId") int detailId) {
		int result = planDetailService.deletePlanDetail(detailId);
		return new ResponseEntity<>(result, result == 1 ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}
}
