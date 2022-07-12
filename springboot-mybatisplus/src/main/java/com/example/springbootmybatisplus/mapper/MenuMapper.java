package com.example.springbootmybatisplus.mapper;

import com.example.springbootmybatisplus.entity.Menu;
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

public interface MenuMapper extends BaseMapper<Menu> {


    List<Map<String, Object>> getMainMenu(Integer id);

    int checkContainSubmenu(int id);

    List<Map<String, Object>> getSecondMenu(int id, int role_id);
}
