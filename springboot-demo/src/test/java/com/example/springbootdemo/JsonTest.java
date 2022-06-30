package com.example.springbootdemo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonTest {
    @JsonProperty(value = "id")
    String id;
    @JsonProperty(value = "name")
    String name;
}
