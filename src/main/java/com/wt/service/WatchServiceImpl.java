package com.wt.service;

import com.wt.dao.WatchMapper;
import com.wt.pojo.Watch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WatchServiceImpl implements WatchService{
    @Autowired
    WatchMapper watchMapper;

    @Override
    public List<Watch> getAllWatchInfo() {
        return watchMapper.getAllWatchInfo();
    }

    @Override
    public List<Watch> getWatchInfoByUserId(Integer id) {
        return watchMapper.getWatchInfoByUserId(id);
    }

    @Override
    public List<Watch> getWatchInfoByUserIdAndState(Integer id, Integer state) {
        return watchMapper.getWatchInfoByUserIdAndState(id,state);
    }

    @Override
    public int insertAWatchInfoByAW(Integer animeId, Integer userId) {
        return watchMapper.insertAWatchInfoByAW(animeId,userId);
    }

    @Override
    public int deleteAWatchInfoByWatchId(Integer watchId) {
        return watchMapper.deleteAWatchInfoByWatchId(watchId);
    }

    @Override
    public Watch getWatchInfoByWatchId(Integer watchId) {
        return watchMapper.getWatchInfoByWatchId(watchId);
    }

    @Override
    public int updateWatchInfo(Watch watch) {
        return watchMapper.updateWatchInfo(watch);
    }

    @Override
    public List<Watch> getWatchInfoByAnimeId(Integer animeId) {
        return watchMapper.getWatchInfoByAnimeId(animeId);
    }

    @Override
    public int deleteWatchInfosByUserId(Integer userId) {
        return watchMapper.deleteWatchInfosByUserId(userId);
    }
}
