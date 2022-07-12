package com.example.springbootmybatisplus.mapper;

import com.example.springbootmybatisplus.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

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

public interface RoleMapper extends BaseMapper<Role> {

    List<Map<String, Object>> getRoleList(Integer id);

    Object findAll();

    List<Role> getRoleByUserId(Integer userid);
}
