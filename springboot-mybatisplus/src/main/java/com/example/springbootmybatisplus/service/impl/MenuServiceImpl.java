package com.example.springbootmybatisplus.service.impl;

import com.example.springbootmybatisplus.entity.MenuEntity;
import com.example.springbootmybatisplus.mapper.MenuMapper;
import com.example.springbootmybatisplus.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yzg
 * @since 2022-07-12
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, MenuEntity> implements IMenuService {

}
