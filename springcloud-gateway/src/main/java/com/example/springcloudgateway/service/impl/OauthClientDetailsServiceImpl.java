package com.example.springcloudgateway.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springcloudgateway.entity.OauthClientDetailsEntity;
import com.example.springcloudgateway.mapper.OauthClientDetailsMapper;
import com.example.springcloudgateway.service.IOauthClientDetailsService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sun
 * @since 2022-07-15
 */
@Service
public class OauthClientDetailsServiceImpl extends ServiceImpl<OauthClientDetailsMapper, OauthClientDetailsEntity> implements IOauthClientDetailsService {

}
