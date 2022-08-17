package com.example.springcloudLogin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springcloudLogin.entity.RefreshTokenEntity;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yzg
 * @since 2022-07-17
 */
public interface IRefreshTokenService extends IService<RefreshTokenEntity> {

    String checkLogin(String userName);

    void insertToken(RefreshTokenEntity refreshTokenEntity);
}
