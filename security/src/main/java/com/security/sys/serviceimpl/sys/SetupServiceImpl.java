package com.security.sys.serviceimpl.sys;

import com.security.sys.dao.sys.RoleDao;
import com.security.sys.entity.sys.Role;
import com.security.sys.service.sys.SetupService;
import com.security.sys.util.ResponseMsgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class SetupServiceImpl implements SetupService {

    @Autowired
    RoleDao roleDao;


    @Override
    public void roleAdd(Role role, HttpServletResponse response) {
        try {
            roleDao.save(role);
            ResponseMsgUtil.sendSuccessMsg("",null,response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void roleChange(Role role, HttpServletResponse response) {
        try {
            roleDao.update(role);
            ResponseMsgUtil.sendSuccessMsg("",null,response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
