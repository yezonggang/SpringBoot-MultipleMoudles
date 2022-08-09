package com.example.springcloudLogin.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OauthTokenEntity {
    @JsonProperty(value="username")
    private String userName;
    @JsonProperty(value="password")
    private String passWord;
    @JsonProperty(value="client_id")
    private String clientId;
    @JsonProperty(value="grant_type")
    private String grantType;
    @JsonProperty(value="client_secret")
    private String clientSecret;
    @JsonProperty(value="scope")
    private String scope;
}
