package com.example.springbootmybatisplus.service.impl;

import com.example.springbootmybatisplus.entity.Permission;
import com.example.springbootmybatisplus.mapper.PermissionMapper;
import com.example.springbootmybatisplus.service.IPermissionService;
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
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

}
