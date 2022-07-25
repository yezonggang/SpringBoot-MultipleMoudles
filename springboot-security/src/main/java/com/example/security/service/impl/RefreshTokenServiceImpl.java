package com.example.security.service.impl;

import com.example.security.entity.RefreshTokenEntity;
import com.example.security.mapper.RefreshTokenMapper;
import com.example.security.service.IRefreshTokenService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yzg
 * @since 2022-07-17
 */
@Service
public class RefreshTokenServiceImpl extends ServiceImpl<RefreshTokenMapper, RefreshTokenEntity> implements IRefreshTokenService {

}
