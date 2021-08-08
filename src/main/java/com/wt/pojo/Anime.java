package com.wt.pojo;

import java.util.Date;

public class Anime {
    private int animeId;
    private String animeName;
    private int episode;
    private Date playDate;
    private String director;
    private String company;
    private String poster;
    private String recordNumber;

    public Anime() {
    }

    public Anime(int animeId, String animeName, int episode, Date playDate, String director, String company, String poster, String recordNumber) {
        this.animeId = animeId;
        this.animeName = animeName;
        this.episode = episode;
        this.playDate = playDate;
        this.director = director;
        this.company = company;
        this.poster = poster;
        this.recordNumber = recordNumber;
    }

    public int getAnimeId() {
        return animeId;
    }

    public void setAnimeId(int animeId) {
        this.animeId = animeId;
    }

    public String getAnimeName() {
        return animeName;
    }

    public void setAnimeName(String animeName) {
        this.animeName = animeName;
    }

    public int getEpisode() {
        return episode;
    }

    public void setEpisode(int episode) {
        this.episode = episode;
    }

    public Date getPlayDate() {
        return playDate;
    }

    public void setPlayDate(Date playDate) {
        this.playDate = playDate;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getRecordNumber() {
        return recordNumber;
    }

    public void setRecordNumber(String recordNumber) {
        this.recordNumber = recordNumber;
    }

    @Override
    public String toString() {
        return "Anime{" +
                "animeId=" + animeId +
                ", animeName='" + animeName + '\'' +
                ", episode=" + episode +
                ", playDate=" + playDate +
                ", director='" + director + '\'' +
                ", company='" + company + '\'' +
                ", poster='" + poster + '\'' +
                ", recordNumber='" + recordNumber + '\'' +
                '}';
    }
}
