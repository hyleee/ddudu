package com.ssafy.ddudu.model.dao;

import com.ssafy.ddudu.model.dto.WeeklyStat;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WeeklyStatDao {
    List<WeeklyStat> getWeeklyStats(@Param("userId") String userId);
}
