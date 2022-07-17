package com.example.springbootmybatisplus.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.springbootmybatisplus.entity.RefreshTokenEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yzg
 * @since 2022-07-17
 */
public interface RefreshTokenMapper extends BaseMapper<RefreshTokenEntity> {

    String getRefreshToken(String username);

    int selectCount(LambdaQueryWrapper<RefreshTokenEntity> queryWrapper);
}
