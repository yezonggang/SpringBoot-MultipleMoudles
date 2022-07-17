package com.example.springbootmybatisplus.mapper;

import com.example.springbootmybatisplus.entity.RoleEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yzg
 * @since 2022-07-17
 */
public interface RoleMapper extends BaseMapper<RoleEntity> {

    Object findAll();
}
