package com.example.springcloudLogin.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginEntity {
    @JsonProperty(value="username")
    private String username;
    @JsonProperty(value="password")
    private String password;
}
