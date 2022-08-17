package com.example.springcloudLogin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springcloudLogin.entity.RefreshTokenEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yzg
 * @since 2022-07-17
 */
@Mapper
public interface RefreshTokenMapper extends BaseMapper<RefreshTokenEntity> {

}
