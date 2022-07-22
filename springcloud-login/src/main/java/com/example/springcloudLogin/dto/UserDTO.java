package com.example.springcloudLogin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class UserDTO {
    private Long id;
    private String name;
    private String password;
    private Integer power;
    private List<String> role;
}
