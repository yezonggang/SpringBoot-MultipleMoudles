package com.security.sys.controller;

import com.security.sys.entity.sys.Role;
import com.security.sys.service.sys.SetupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class SetupController {

    @Autowired
    SetupService setupService;

    @PostMapping("/role")
    public void roleAdd(Role role, HttpServletResponse response){
        setupService.roleAdd(role,response);
    }
    @PutMapping("/role")
    public void roleChange(Role role,HttpServletResponse response){
        setupService.roleChange(role,response);
    }
}
