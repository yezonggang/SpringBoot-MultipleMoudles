package com.example.springbootmybatisplus.mapper;

import com.example.springbootmybatisplus.entity.RoleEntity;
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

public interface RoleMapper extends BaseMapper<RoleEntity> {

    List<Map<String, Object>> getRoleList(Integer id);

    Object findAll();

    List<RoleEntity> getRoleByUserId(Integer userid);
/*
    @Query(nativeQuery = true,value = "select role_id  from role_user where user_id=:userid")
    List<Integer> getRoleIdsByUserId(int userid);

    @Query(nativeQuery = true,value = "select r.* from role_user ru left join user u on ru.user_id = u.id left join " +
            "role r on ru.role_id = r.id where u.id=:userid ")
    List<Map<String,Object>> getRoleList(Integer userid);

    @Query(nativeQuery = true,value = "select r.* from  role_user ru left join user u on ru.user_id = u.id left join role r on ru.role_id = r.id where u.id=:userId ")
    List<Role> getRoleByUserId(int userId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true,value = "update role set name= :#{#role.name}, " +
            "code= :#{#role.code}, " +
            "des= :#{#role.des} " +
            "where id = :#{#role.id}")
    void update(Role role);*/
}
