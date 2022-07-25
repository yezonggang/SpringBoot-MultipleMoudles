package com.example.security.vo;

import lombok.Data;

import java.util.List;

@Data
public class LoginInfoVO {
    public List<String> roles;
    public String name;
    public String avatar;
    public String introductions;
}
