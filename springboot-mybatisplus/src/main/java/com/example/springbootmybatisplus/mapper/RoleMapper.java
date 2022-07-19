package com.example.springbootmybatisplus.mapper;

import com.example.springbootmybatisplus.entity.RoleEntity;
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
public interface RoleMapper extends BaseMapper<RoleEntity> {

    Object findAll();
}
