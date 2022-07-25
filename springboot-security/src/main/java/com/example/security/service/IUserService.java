package com.example.security.service;

import com.example.security.entity.UserEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import response.ResponseData;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yzg
 * @since 2022-07-17
 */
public interface IUserService extends IService<UserEntity> {
    public ResponseData loginInfo(HttpServletRequest request);
}
