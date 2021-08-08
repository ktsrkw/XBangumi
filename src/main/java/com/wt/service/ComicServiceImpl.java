package com.wt.service;

import com.wt.dao.ComicMapper;
import com.wt.pojo.Comic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComicServiceImpl implements ComicService{
    @Autowired
    ComicMapper comicMapper;

    @Override
    public int addAComic(Comic comic) {
        return comicMapper.addAComic(comic);
    }

    @Override
    public List<Comic> getAllComic() {
        return comicMapper.getAllComic();
    }

    @Override
    public int deleteAComicByComicId(Integer comicId) {
        return comicMapper.deleteAComicByComicId(comicId);
    }

    @Override
    public Comic getComicByComicId(Integer comicId) {
        return comicMapper.getComicByComicId(comicId);
    }

    @Override
    public int updateComicInfo(Comic comic) {
        return comicMapper.updateComicInfo(comic);
    }

    @Override
    public List<Comic> getComicByUserInput(String userQueryInput) {
        return comicMapper.getComicByUserInput(userQueryInput);
    }

    @Override
    public Comic getComicByISBN(String isbn) {
        return comicMapper.getComicByISBN(isbn);
    }
}
