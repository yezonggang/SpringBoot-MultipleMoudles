package com.example.springbootmybatisplus.service.impl;

import com.example.springbootmybatisplus.entity.RoleUserEntity;
import com.example.springbootmybatisplus.mapper.RoleUserMapper;
import com.example.springbootmybatisplus.service.IRoleUserService;
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
public class RoleUserServiceImpl extends ServiceImpl<RoleUserMapper, RoleUserEntity> implements IRoleUserService {

}
