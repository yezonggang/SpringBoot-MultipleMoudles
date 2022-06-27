package com.example.springbootdemo.model.entitiy;

public class Crontable {
    public int id;
    public String info;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Crontable(int id, String info) {
        this.id = id;
        this.info = info;
    }
}
