package com.security.sys.service.sys;

import com.security.sys.entity.sys.Role;

import javax.servlet.http.HttpServletResponse;

public interface SetupService {

    void roleAdd(Role role, HttpServletResponse response);
    void roleChange(Role role,HttpServletResponse response);
}
