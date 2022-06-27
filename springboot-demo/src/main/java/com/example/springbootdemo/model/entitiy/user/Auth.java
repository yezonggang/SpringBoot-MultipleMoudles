package com.example.springbootdemo.model.entitiy.user;


import java.util.List;

//权限表，角色对应权限
//权限也对应路径列表，比如:/documents等

public class Auth {
    int id;
    String name;
    String comment;
    List<String> urlList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<String> getUrlList() {
        return urlList;
    }

    public void setUrlList(List<String> urlList) {
        this.urlList = urlList;
    }

    public Auth(int id, String name, String comment, List<String> urlList) {
        this.id = id;
        this.name = name;
        this.comment = comment;
        this.urlList = urlList;
    }
}
