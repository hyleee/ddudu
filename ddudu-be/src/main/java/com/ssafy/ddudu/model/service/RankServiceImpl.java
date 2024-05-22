package com.ssafy.ddudu.model.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ssafy.ddudu.model.dao.RankDao;
import com.ssafy.ddudu.model.dto.Rank;

@Service
public class RankServiceImpl implements RankService {

    @Autowired
    private RankDao rankDao;

    @Override
    public List<Rank> getChestRank() {
        return rankDao.findChestRank();
    }

    @Override
    public List<Rank> getBackRank() {
        return rankDao.findBackRank();
    }

    @Override
    public List<Rank> getShouldersRank() {
        return rankDao.findShouldersRank();
    }

    @Override
    public List<Rank> getLegsRank() {
        return rankDao.findLegsRank();
    }

    @Override
    public List<Rank> getArmsRank() {
        return rankDao.findArmsRank();
    }

    @Override
    public List<Rank> getCoreRank() {
        return rankDao.findCoreRank();
    }
}
