package com.example.springcloudLogin.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OauthTokenEntity {
    @JsonProperty(value="username")
    private String username;
    @JsonProperty(value="password")
    private String password;
    @JsonProperty(value="client_id")
    private String client_id;
    @JsonProperty(value="grant_type")
    private String grant_type;
    @JsonProperty(value="client_secret")
    private String client_secret;
    @JsonProperty(value="scope")
    private String scope;
}
