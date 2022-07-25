package com.example.security.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.security.entity.RefreshTokenEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yzg
 * @since 2022-07-17
 */
@Mapper
@Component
public interface RefreshTokenMapper extends BaseMapper<RefreshTokenEntity> {

    String getRefreshToken(String username);

    int selectCount(LambdaQueryWrapper<RefreshTokenEntity> queryWrapper);
}
