package com.wt.service;

import com.wt.pojo.Read;

import java.util.List;

public interface ReadService {
    //得到所有阅读信息
    List<Read> getAllReadInfo();

    //根据用户id得到信息
    List<Read> getReadInfoByUserId(Integer userId);

    //根据用户id和状态得到信息
    List<Read> getReadInfoByUserIdAndState(Integer userId,Integer state);

    //根据comicId和userId插入一条数据
    int insertAReadInfoByCU(Integer comicId,Integer userId);

    //根据readId删除一条记录
    int deleteAReadInfoByReadId(Integer readId);

    //根据readId得到一条记录
    Read getAReadInfoByReadId(Integer readId);

    //更新read信息
    int updateReadInfo(Read read);

    //根据comicId获得readInfo
    List<Read> getReadInfoByComicId(Integer comicId);

    //根据用户id删除阅读记录
    int deleteReadInfosByUserId(Integer userId);
}
