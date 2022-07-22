package com.example.springcloudLogin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springcloudLogin.entity.OauthClientEntity;
import com.example.springcloudLogin.mapper.OauthClientMapper;
import com.example.springcloudLogin.service.OauthClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * OAuth2客户端业务实现类
 *
 * @author haoxr
 * @date 2020-11-06
 */
@Service
@RequiredArgsConstructor
public class OauthClientServiceImpl extends ServiceImpl<OauthClientMapper, OauthClientEntity> implements OauthClientService {

}
