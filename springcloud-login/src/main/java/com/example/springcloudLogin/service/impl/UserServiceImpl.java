package com.example.springcloudLogin.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springcloudLogin.entity.UserEntity;
import com.example.springcloudLogin.vo.UserEntityVO;
import com.example.springcloudLogin.mapper.UserMapper;
import com.github.yulichang.query.MPJQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import utils.JwtUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserDetailsService, IService<UserEntity> {

    @Autowired
    private UserMapper userMapper;
    @Override
    public UserEntityVO loadUserByUsername(String username) throws UsernameNotFoundException {
        username=StringUtils.isEmpty(username)? JwtUtils.getJwtPayload().get("user_name").toString():username;
        UserEntityVO user = userMapper.selectJoinOne(UserEntityVO.class, new MPJQueryWrapper<UserEntityVO>()
                .select("t.id,t.username as username,t.password ,group_concat(DISTINCT ro.`name`) as roles")
                .leftJoin("role_user AS ru ON ru.user_id = t.id")
                .leftJoin("role ro ON ro.id = ru.role_id")
                .groupBy("t.id ,t.username,t.password")
                .eq("t.username",username));
        if (!ObjectUtils.isEmpty(user.getRoles())) {
            List<SimpleGrantedAuthority> list = new ArrayList<>();
            Arrays.asList(user.getRoles().split(",")).forEach(e -> list.add(new SimpleGrantedAuthority(e)));
            user.setAuthorities(list);
            System.out.println(user);
        }
        if (!user.isEnabled()) {
            throw new DisabledException("ACCOUNT_DISABLED");
        } else if (!user.isAccountNonLocked()) {
            throw new LockedException("ACCOUNT_LOCKED");
        }  else if (!user.isCredentialsNonExpired()) {
            throw new CredentialsExpiredException("CREDENTIALS_EXPIRED");
        }
        return user;
    }
}
