package com.example.security.service.impl;

import com.example.security.entity.RoleUserEntity;
import com.example.security.mapper.RoleUserMapper;
import com.example.security.service.IRoleUserService;
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
public class RoleUserServiceImpl extends ServiceImpl<RoleUserMapper, RoleUserEntity> implements IRoleUserService {

}
