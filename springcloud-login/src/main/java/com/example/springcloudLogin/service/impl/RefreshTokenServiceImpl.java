package com.example.springcloudLogin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springcloudLogin.entity.RefreshTokenEntity;
import com.example.springcloudLogin.mapper.RefreshTokenMapper;
import com.example.springcloudLogin.service.IRefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

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

    @Autowired
    private  RefreshTokenMapper refreshTokenMapper;
    @Override
    public String checkLogin(String userName) {
        LambdaQueryWrapper<RefreshTokenEntity> queryWrapper = new QueryWrapper<RefreshTokenEntity>().lambda()
                .eq(RefreshTokenEntity::getUsename, userName);
        RefreshTokenEntity refreshTokenEntity = refreshTokenMapper.selectOne(queryWrapper);
        if (ObjectUtils.isEmpty(refreshTokenEntity)) {
            return null;
        }
        return refreshTokenEntity.getToken();
    }

    @Override
    public void insertToken(RefreshTokenEntity entity) {
        //查询该用户是否存在token；若存在则更新，不存在则插入
        LambdaQueryWrapper<RefreshTokenEntity> queryWrapper = new QueryWrapper<RefreshTokenEntity>().lambda()
                .eq(RefreshTokenEntity::getUsename, entity.getUsename());
        RefreshTokenEntity refreshTokenEntity = refreshTokenMapper.selectOne(queryWrapper);
        if (!ObjectUtils.isEmpty(refreshTokenEntity)) {
            entity.setId(refreshTokenEntity.getId());
            this.updateById(entity);
        } else {
            this.save(entity);
        }
    }
}
