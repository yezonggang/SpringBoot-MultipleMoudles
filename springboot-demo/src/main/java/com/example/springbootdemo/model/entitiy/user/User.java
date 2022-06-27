package com.example.springbootdemo.model.entitiy.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties
public class User {
    @JsonProperty("id")
    String id;
    @JsonProperty("username")
    String username;
    @JsonProperty("password")
    String passwd;
    String tel;
    String email;
    String address;

    public User(String id, String username, String passwd, String tel, String email, String address) {
        this.id = id;
        this.username = username;
        this.passwd = passwd;
        this.tel = tel;
        this.email = email;
        this.address = address;
    }

    public User(String id, String username, String passwd) {
        this.id = id;
        this.username = username;
        this.passwd = passwd;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User() {
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
