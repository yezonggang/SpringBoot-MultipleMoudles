package com.example.springbootmybatisplus.service;

import com.example.springbootmybatisplus.entity.RefreshToken;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yzg
 * @since 2022-07-12
 */
public interface IRefreshTokenService extends IService<RefreshToken> {

    //    @Query(nativeQuery = true,value = "select count(1) from refresh_token where username=:username")
    int existRefreshToken(String username);

    //     @Query(nativeQuery = true,value = "update refresh_token set token= :#{#token.token} " +
    //            "where username = :#{#token.username}")
    void updateRefreshToken(RefreshToken token);
}
