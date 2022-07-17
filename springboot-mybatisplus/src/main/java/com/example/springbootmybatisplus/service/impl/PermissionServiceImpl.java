package com.example.springbootmybatisplus.service.impl;

import com.example.springbootmybatisplus.entity.PermissionEntity;
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
 * @since 2022-07-17
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, PermissionEntity> implements IPermissionService {

}
