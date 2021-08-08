package com.wt.service;

import com.wt.pojo.Comic;

import java.util.List;

public interface ComicService {
    //添加一条漫画条目
    int addAComic(Comic comic);

    //查询所有漫画条目
    List<Comic> getAllComic();

    //根据id删除一个漫画条目
    int deleteAComicByComicId(Integer comicId);

    //根据id得到comic
    Comic getComicByComicId(Integer comicId);

    //更新漫画条目信息
    int updateComicInfo(Comic comic);

    //根据用户输入模糊查询
    List<Comic> getComicByUserInput(String userQueryInput);

    //根据ISBN查找记录
    Comic getComicByISBN(String isbn);
}
