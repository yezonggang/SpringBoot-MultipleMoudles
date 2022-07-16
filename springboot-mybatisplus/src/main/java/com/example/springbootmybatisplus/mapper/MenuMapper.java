package com.example.springbootmybatisplus.mapper;

import com.example.springbootmybatisplus.entity.MenuEntity;
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

public interface MenuMapper extends BaseMapper<MenuEntity> {


    List<Map<String, Object>> getMainMenu(Integer id);

    int checkContainSubmenu(int id);

    List<Map<String, Object>> getSecondMenu(int id, int role_id);

/*
    @Query(nativeQuery = true,value = "select id, name as label from permission where pid=:pid")
    List<Map<String,Object>> getMenuByPid(int pid);
    @Query(nativeQuery = true,value = "select sm.* from permission_role smr left join permission sm on smr.permission_id = sm.id where sm.pid='0' and smr.role_id=:role_id order by sm.sort asc")
    List<Menu> getMainMenuList(int role_id);
    @Query(nativeQuery = true,value = "select sm.* from permission_role smr left join permission sm on smr.permission_id = sm.id where sm.pid='0' and smr.role_id=:role_id order by sm.sort asc")
    List<Map<String,Object>> getMainMenu(int role_id);
    @Query(nativeQuery = true,value = "select smr.role_id,sm.id,sm.name,sm.icon,sm.description,sm.pid parent_id,sm.url "
            + "from permission_role smr left join permission sm on smr.permission_id = sm.id "
            + "where sm.pid=:pid and smr.role_id=:role_id order by sm.sort asc")
    List<Map<String,Object>> getSecondMenu(int pid,int role_id);
    @Query(nativeQuery = true,value = "select  sm.id,sm.name,sm.url,sm.pid parent_id, 'true' as is_show "
            + "from permission_role smr left join permission sm on smr.permission_id = sm.id "
            + "where sm.pid=:pid and smr.role_id=:role_id order by sm.sort asc")
    List<Map<String,Object>> getLowerLevelMenu(int pid,int role_id);
    @Query(nativeQuery = true,value = "select  id from permission where pid=:pid order by sort asc")
    List<Integer> getMenuIdByPid(int pid);
    @Query(nativeQuery = true,value = "select * from permission")
    List<Menu> getAllMenu();
    @Query(nativeQuery = true,value = "select count(1) from permission where pid=:pid")
    int checkContainSubmenu(int pid);*/
}
