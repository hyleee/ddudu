package com.ssafy.ddudu.model.service;

import com.ssafy.ddudu.model.dto.WeeklyStat;

import java.util.List;

public interface WeeklyStatService {
    List<WeeklyStat> getWeeklyStats(String userId);
}
