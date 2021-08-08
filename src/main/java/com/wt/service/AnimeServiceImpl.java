package com.wt.service;

import com.wt.dao.AnimeMapper;
import com.wt.pojo.Anime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimeServiceImpl implements AnimeService{
    @Autowired
    AnimeMapper animeMapper;


    @Override
    public int addAnAnime(Anime anime) {
        return animeMapper.addAnAnime(anime);
    }

    @Override
    public List<Anime> getAllAnime() {
        return animeMapper.getAllAnime();
    }

    @Override
    public Anime getAnimeById(Integer animeId) {
        return animeMapper.getAnimeById(animeId);
    }

    @Override
    public int updateAnimeInfo(Anime anime) {
        return animeMapper.updateAnimeInfo(anime);
    }

    @Override
    public int deleteAnAnime(Integer animeId) {
        return animeMapper.deleteAnAnime(animeId);
    }

    @Override
    public List<Anime> getAnimeByUserInput(String userQueryInput) {
        return animeMapper.getAnimeByUserInput(userQueryInput);
    }

    @Override
    public Anime getAnimeByRecordNumber(String recordNumber) {
        return animeMapper.getAnimeByRecordNumber(recordNumber);
    }
}
