package com.example.springbootdemo.model.entitiy.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

//角色信息，比如超用户admin/普通用户normal/运维人员op,等
@JsonIgnoreProperties
public class Role {
    @JsonProperty("name")
    public String name;
    @JsonProperty("roles")
    public List<String> roleList;
    @JsonProperty("introduction")
    public String introduction;
    @JsonProperty("avatar")
    public String avatar;

    public Role(String name, List<String> roleList, String introduction, String avatar) {
        this.name = name;
        this.roleList = roleList;
        this.introduction = introduction;
        this.avatar = avatar;
    }

}
