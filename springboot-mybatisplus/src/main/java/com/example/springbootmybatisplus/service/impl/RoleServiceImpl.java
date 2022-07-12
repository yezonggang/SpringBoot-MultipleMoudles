package com.example.springbootmybatisplus.service.impl;

import com.example.springbootmybatisplus.entity.Role;
import com.example.springbootmybatisplus.mapper.RoleMapper;
import com.example.springbootmybatisplus.service.IRoleService;
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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
