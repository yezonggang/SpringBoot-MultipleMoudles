package com.example.springbootmybatisplus.mapper;

import com.example.springbootmybatisplus.entity.RefreshToken;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yzg
 * @since 2022-07-12
 */
@Mapper
@Component

public interface RefreshTokenMapper extends BaseMapper<RefreshToken> {

    String getRefreshToken(String username);
}
