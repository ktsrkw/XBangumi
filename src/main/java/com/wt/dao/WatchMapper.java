package com.wt.dao;

import com.wt.pojo.Watch;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface WatchMapper {
    //得到所有进度信息
    List<Watch> getAllWatchInfo();

    //根据用户id得到信息
    List<Watch> getWatchInfoByUserId(Integer id);

    //根据用户信息与状态得到观看信息
    List<Watch> getWatchInfoByUserIdAndState(Integer id,Integer state);

    //根据animeId和watchId插入数据
    int insertAWatchInfoByAW(Integer animeId,Integer userId);

    //根据watchId删除一条数据
    int deleteAWatchInfoByWatchId(Integer watchId);

    //根据watchId得到信息
    Watch getWatchInfoByWatchId(Integer watchId);

    //修改watch的信息
    int updateWatchInfo(Watch watch);

    //根据animeId查watch表，得到watchId的list
    List<Watch> getWatchInfoByAnimeId(Integer animeId);

    //根据用户id删除观看记录
    int deleteWatchInfosByUserId(Integer userId);

}
