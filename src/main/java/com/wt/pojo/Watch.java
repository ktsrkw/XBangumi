package com.wt.pojo;

public class Watch {
    private int watchId;
    private int animeId;
    private int userId;
    private int currentEpisode;
    private int currentState;
    private int score;
    private String comment;

    public Watch() {
    }

    public Watch(int watchId, int animeId, int userId, int currentEpisode, int currentState, int score, String comment) {
        this.watchId = watchId;
        this.animeId = animeId;
        this.userId = userId;
        this.currentEpisode = currentEpisode;
        this.currentState = currentState;
        this.score = score;
        this.comment = comment;
    }

    public int getWatchId() {
        return watchId;
    }

    public void setWatchId(int watchId) {
        this.watchId = watchId;
    }

    public int getAnimeId() {
        return animeId;
    }

    public void setAnimeId(int animeId) {
        this.animeId = animeId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCurrentEpisode() {
        return currentEpisode;
    }

    public void setCurrentEpisode(int currentEpisode) {
        this.currentEpisode = currentEpisode;
    }

    public int getCurrentState() {
        return currentState;
    }

    public void setCurrentState(int currentState) {
        this.currentState = currentState;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Watch{" +
                "watchId=" + watchId +
                ", animeId=" + animeId +
                ", userId=" + userId +
                ", currentEpisode=" + currentEpisode +
                ", currentState=" + currentState +
                ", score=" + score +
                ", comment='" + comment + '\'' +
                '}';
    }
}
