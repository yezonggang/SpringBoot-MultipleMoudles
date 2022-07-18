package com.example.springbootmybatisplus.service;

import com.example.springbootmybatisplus.entity.UserEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springbootmybatisplus.utils.ResponseData;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
