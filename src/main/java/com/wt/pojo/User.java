package com.wt.pojo;

import java.util.Date;

public class User {
    private int id;
    private String username;
    private String email;
    private String password;
    private String tele;
    private Date birthday;
    private String introduction;

    public User() {
    }

    public User(int id, String username, String email, String password, String tele, Date birthday, String introduction) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.tele = tele;
        this.birthday = birthday;
        this.introduction = introduction;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTele() {
        return tele;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", tele='" + tele + '\'' +
                ", birthday=" + birthday +
                ", introduction='" + introduction + '\'' +
                '}';
    }
}
