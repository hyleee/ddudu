package com.ssafy.ddudu.model.service;

import com.ssafy.ddudu.model.dao.WeeklyStatDao;
import com.ssafy.ddudu.model.dto.WeeklyStat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeeklyStatServiceImpl implements WeeklyStatService {

    @Autowired
    private WeeklyStatDao weeklyStatDao;

    @Override
    public List<WeeklyStat> getWeeklyStats(String userId) {
        return weeklyStatDao.getWeeklyStats(userId);
    }
}
