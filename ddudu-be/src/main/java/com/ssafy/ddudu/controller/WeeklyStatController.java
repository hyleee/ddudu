package com.ssafy.ddudu.controller;

import com.ssafy.ddudu.model.dto.WeeklyStat;
import com.ssafy.ddudu.model.service.WeeklyStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stats")
//@CrossOrigin(origins = "*")
public class WeeklyStatController {

    @Autowired
    private WeeklyStatService weeklyStatService;

    @GetMapping("/weekly/{userId}")
    public List<WeeklyStat> getWeeklyStats(@PathVariable String userId) {
        return weeklyStatService.getWeeklyStats(userId);
    }
}
