package com.example.springbootmybatisplus.service.impl;

import com.example.springbootmybatisplus.entity.RefreshToken;
import com.example.springbootmybatisplus.mapper.RefreshTokenMapper;
import com.example.springbootmybatisplus.service.IRefreshTokenService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yzg
 * @since 2022-07-12
 */
@Service
public class RefreshTokenServiceImpl extends ServiceImpl<RefreshTokenMapper, RefreshToken> implements IRefreshTokenService {

    @Override
    public int existRefreshToken(String username) {
        return 0;
    }

    @Override
    public void updateRefreshToken(RefreshToken token) {

    }
}
