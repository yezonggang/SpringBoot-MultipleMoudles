package com.example.springbootmybatisplus.service;

import com.example.springbootmybatisplus.entity.UserEntity;
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
