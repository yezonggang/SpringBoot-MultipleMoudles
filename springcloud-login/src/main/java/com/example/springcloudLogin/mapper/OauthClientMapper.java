package com.example.springcloudLogin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springcloudLogin.entity.OauthClientEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OauthClientMapper extends BaseMapper<OauthClientEntity> {
}
