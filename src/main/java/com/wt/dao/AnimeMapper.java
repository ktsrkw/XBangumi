package com.wt.dao;

import com.wt.pojo.Anime;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AnimeMapper {
    //添加一条动画条目
    int addAnAnime(Anime anime);

    //查询所有动画条目
    List<Anime> getAllAnime();

    //根据id得到动画条目
    Anime getAnimeById(Integer animeId);

    //更新动画条目的信息
    int updateAnimeInfo(Anime anime);

    //根据id删除动画条目
    int deleteAnAnime(Integer animeId);

    //根据输入模糊查询
    List<Anime> getAnimeByUserInput(String userQueryInput);

    //根据备案号查找记录
    Anime getAnimeByRecordNumber(String recordNumber);

}
