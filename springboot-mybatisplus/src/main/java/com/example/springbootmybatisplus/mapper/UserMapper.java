package com.example.springbootmybatisplus.mapper;

import com.example.springbootmybatisplus.entity.UserEntity;
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
public interface UserMapper extends BaseMapper<UserEntity> {

}
