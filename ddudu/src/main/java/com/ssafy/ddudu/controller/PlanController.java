package com.ssafy.ddudu.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ddudu.model.dto.Plan;
import com.ssafy.ddudu.model.service.PlanService;

import io.swagger.v3.oas.annotations.Operation;

import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
@RestController
@RequestMapping("/plan")
@CrossOrigin(origins = "*")
public class PlanController {

	@Autowired
	PlanService planService;


	@Operation(summary = "해당 날짜 운동 계획 조회", description = "해당 날짜 운동 계획을 조회합니다.")
	@GetMapping("/{userId}/{exerciseDate}")
	public ResponseEntity<?> getPlanByDate(
	        @PathVariable("exerciseDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate exerciseDate, 
	        @PathVariable("userId") String userId) {

	    Plan plan = planService.getPlanByDateAndUserId(exerciseDate, userId); // Service layer also needs to handle LocalDate
	    if(plan == null) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No plan found for the given date and user");
	    }
	    return new ResponseEntity<>(plan, HttpStatus.OK);
	}

	@Operation(summary = "해당 날짜 운동 계획 작성", description = "해당 날짜 운동 계획을 작성합니다.")
	@PostMapping("{exerciseDate}")
	public ResponseEntity<?> createPlan(
			@PathVariable("exerciseDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date exerciseDate,
			@RequestBody Plan plan) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dateWithoutTime;
		try {
			dateWithoutTime = sdf.parse(sdf.format(exerciseDate));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Invalid date format");
		}

		plan.setExerciseDate(dateWithoutTime);
		
		int result = planService.createPlan(plan);
		return new ResponseEntity<>(result, result == 1 ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}

	@Operation(summary = "해당 날짜 운동 계획 삭제", description = "해당 날짜 운동 계획을 삭제합니다.")
	@DeleteMapping("{planId}")
	public ResponseEntity<?> deletePlan(@PathVariable("planId") int planId) {
		int result = planService.deletePlan(planId);
		return new ResponseEntity<>(result, result == 1 ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}
	

}