package com.wt.pojo;

import java.util.Date;

public class Comic {
    private int comicId;
    private String comicName;
    private int page;
    private Date publicationDate;
    private String author;
    private String press;
    private String cover;
    private String isbn;

    public Comic() {
    }

    public Comic(int comicId, String comicName, int page, Date publicationDate, String author, String press, String cover, String isbn) {
        this.comicId = comicId;
        this.comicName = comicName;
        this.page = page;
        this.publicationDate = publicationDate;
        this.author = author;
        this.press = press;
        this.cover = cover;
        this.isbn = isbn;
    }

    public int getComicId() {
        return comicId;
    }

    public void setComicId(int comicId) {
        this.comicId = comicId;
    }

    public String getComicName() {
        return comicName;
    }

    public void setComicName(String comicName) {
        this.comicName = comicName;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return "Comic{" +
                "comicId=" + comicId +
                ", comicName='" + comicName + '\'' +
                ", page=" + page +
                ", publicationDate=" + publicationDate +
                ", author='" + author + '\'' +
                ", press='" + press + '\'' +
                ", cover='" + cover + '\'' +
                ", isbn='" + isbn + '\'' +
                '}';
    }
}
