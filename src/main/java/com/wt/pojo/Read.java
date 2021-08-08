package com.wt.pojo;

public class Read {
    private int readId;
    private int comicId;
    private int userId;
    private int currentPage;
    private int currentState;
    private int score;
    private String comment;

    public Read() {
    }

    public Read(int readId, int comicId, int userId, int currentPage, int currentState, int score, String comment) {
        this.readId = readId;
        this.comicId = comicId;
        this.userId = userId;
        this.currentPage = currentPage;
        this.currentState = currentState;
        this.score = score;
        this.comment = comment;
    }

    public int getReadId() {
        return readId;
    }

    public void setReadId(int readId) {
        this.readId = readId;
    }

    public int getComicId() {
        return comicId;
    }

    public void setComicId(int comicId) {
        this.comicId = comicId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
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
        return "Read{" +
                "readId=" + readId +
                ", comicId=" + comicId +
                ", userId=" + userId +
                ", currentPage=" + currentPage +
                ", currentState=" + currentState +
                ", score=" + score +
                ", comment='" + comment + '\'' +
                '}';
    }
}
