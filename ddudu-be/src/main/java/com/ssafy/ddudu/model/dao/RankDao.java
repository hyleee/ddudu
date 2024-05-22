package com.ssafy.ddudu.model.dao;

import java.util.List;

import com.ssafy.ddudu.model.dto.Rank;

public interface RankDao {

    List<Rank> findChestRank();

    List<Rank> findBackRank();

    List<Rank> findShouldersRank();

    List<Rank> findLegsRank();

    List<Rank> findArmsRank();

    List<Rank> findCoreRank();
}
