package com.wt.service;

import com.wt.dao.ReadMapper;
import com.wt.pojo.Read;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReadServiceImpl implements ReadService{
    @Autowired
    ReadMapper readMapper;

    @Override
    public List<Read> getAllReadInfo() {
        return readMapper.getAllReadInfo();
    }

    @Override
    public List<Read> getReadInfoByUserId(Integer userId) {
        return readMapper.getReadInfoByUserId(userId);
    }

    @Override
    public List<Read> getReadInfoByUserIdAndState(Integer userId, Integer state) {
        return readMapper.getReadInfoByUserIdAndState(userId,state);
    }

    @Override
    public int insertAReadInfoByCU(Integer comicId, Integer userId) {
        return readMapper.insertAReadInfoByCU(comicId,userId);
    }

    @Override
    public int deleteAReadInfoByReadId(Integer readId) {
        return readMapper.deleteAReadInfoByReadId(readId);
    }

    @Override
    public Read getAReadInfoByReadId(Integer readId) {
        return readMapper.getAReadInfoByReadId(readId);
    }

    @Override
    public int updateReadInfo(Read read) {
        return readMapper.updateReadInfo(read);
    }

    @Override
    public List<Read> getReadInfoByComicId(Integer comicId) {
        return readMapper.getReadInfoByComicId(comicId);
    }

    @Override
    public int deleteReadInfosByUserId(Integer userId) {
        return readMapper.deleteReadInfosByUserId(userId);
    }
}
