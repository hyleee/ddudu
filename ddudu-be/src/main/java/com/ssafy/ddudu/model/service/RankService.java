package com.ssafy.ddudu.model.service;

import java.util.List;

import com.ssafy.ddudu.model.dto.Rank;

public interface RankService {

    List<Rank> getChestRank();

    List<Rank> getBackRank();

    List<Rank> getShouldersRank();

    List<Rank> getLegsRank();

    List<Rank> getArmsRank();

    List<Rank> getCoreRank();
}
