package com.example.springbootmybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springbootmybatisplus.config.security.JsonWebTokenUtil;
import com.example.springbootmybatisplus.entity.*;
import com.example.springbootmybatisplus.mapper.*;
import com.example.springbootmybatisplus.utils.ApiError;
import com.example.springbootmybatisplus.utils.ApiErrorEnum;
import com.example.springbootmybatisplus.utils.ResponseData;
import com.example.springbootmybatisplus.utils.ResponseMsgUtil;
import com.example.springbootmybatisplus.vo.LoginInfoVO;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author yzg
 * @since 2022-07-12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserDetailsService, IService<UserEntity> {

    @Autowired
    UserMapper userMapper;
    @Autowired
    AccountStateMapper accountStateDao;
    @Autowired
    RefreshTokenMapper refreshTokenDao;
    @Autowired
    JsonWebTokenUtil jwtTokenUtil;
    @Autowired
    RoleUserMapper roleUserMapper;

    @Autowired
    RoleMapper roleDao;

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public UserDetails loadUserByUsername(String s) {
        logger.info(String.format("xxxxxx%s", s));
        LambdaQueryWrapper<UserEntity> queryWrapper = new QueryWrapper<UserEntity>().lambda().eq(UserEntity::getUsername, s);
        UserEntity user = userMapper.selectOne(queryWrapper);
        if (user != null) {
            SysUserDetailEntity detail = new SysUserDetailEntity();
            detail.setId(Math.toIntExact(user.getId()));
            detail.setUsername(user.getUsername());
            detail.setPassword(user.getPassword());
            logger.info(String.format("xxxxxxxxxxuser_id,%s", user.getId()));
            AccountStateEntity accountState = accountStateDao.selectOne(new QueryWrapper<AccountStateEntity>().lambda().eq(AccountStateEntity::getUserid, user.getId()));
/*                detail.setAccountNonExpired(accountState.getAccountNonExpired() == 1);
                detail.setAccountNonLocked(accountState.getAccountNonLocked() == 1);
                detail.setEnabled(accountState.getEnabled() == 1);
                detail.setCredentialsNonExpired(accountState.getCredentialsNonExpired() == 1);*/
            detail.setAccountNonExpired(true);
            detail.setAccountNonLocked(true);
            detail.setEnabled(true);
            detail.setCredentialsNonExpired(true);
            //查询用户权限
            List<GrantedAuthority> authorities = new ArrayList<>();
            // 从RoleUser表拿到admin用户所有的role角色，他可以是admin超用户，也有普通用户权限
            LambdaQueryWrapper<RoleUserEntity> roleUserEntityQueryWrapper = new QueryWrapper<RoleUserEntity>().lambda().eq(RoleUserEntity::getUserId, user.getId());
            List<RoleUserEntity> roleUserEntities = roleUserMapper.selectList(roleUserEntityQueryWrapper);
            for (RoleUserEntity roleUserEntity : roleUserEntities) {
                RoleEntity roleTemp = roleDao.selectOne(new QueryWrapper<RoleEntity>().lambda().eq(RoleEntity::getId, roleUserEntity.getRoleId()));
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority(roleTemp.getCode());
                authorities.add(authority);
            }
            detail.setAuthorities(authorities);
            return detail;
        } else
            throw new UsernameNotFoundException("该账号不存在");
    }

    public ResponseData loginInfo(HttpServletRequest request) {
        // 因为所有url都被过滤，所以这里只需要拿到role就行，不需要考虑token是否过期问题
        String token = request.getHeader(jwtTokenUtil.getHeader());
        LoginInfoVO loginInfoVO = new LoginInfoVO();
        if(StringUtils.hasLength(token) && !token.equals("null")) {
            String username = jwtTokenUtil.getUsernameIgnoreExpiration(token);
            LambdaQueryWrapper<RoleEntity> queryWrapper = new QueryWrapper<RoleEntity>().lambda().eq(RoleEntity::getName, username);
            List<RoleEntity> roleEntities = roleDao.selectList(queryWrapper);
            List<String> roleList = new ArrayList<>();
            for (RoleEntity roleEntity : roleEntities) {
                roleList.add(roleEntity.getName());
            }
            loginInfoVO.setRoles(roleList);
            return ResponseData.success(loginInfoVO);
        }
        return ResponseData.fail(ApiError.from(ApiErrorEnum.TOKEN_EXPIRED));
    }

/*

        public void getMenu (String username, HttpServletRequest request, HttpServletResponse response) throws
        IOException {
            UserEntity entity = userMapper.selectOne(new QueryWrapper<UserEntity>().lambda().eq(UserEntity::getUsername,username));
            Integer userid = entity.getId();
            List<RoleEntity> roles =roleDao.getRoleByUserId(userid);
            //List<Menu> menus = new ArrayList<>();
            List<Map<String, Object>> data = new ArrayList<>();
            for (RoleEntity role : roles) {
                List<Map<String, Object>> mapList = menuDao.getMainMenu(role.getId());
                for (Map<String, Object> one : mapList) {
                    Map<String, Object> temp = new HashMap<>(one);
                    if (menuDao.checkContainSubmenu((int) temp.get("id")) > 0) {
                        data.add(buildSubmenu(temp, role.getId()));
                    } else
                        data.add(temp);
                }
            }
            //因为一个用户可能拥有多个角色，所以菜单可能重复，这一步为根据id去重
            List<Map<String, Object>> list = data.stream().collect(
                    Collectors.collectingAndThen(
                            Collectors.toCollection(
                                    () -> new TreeSet<>(Comparator.comparing(m -> m.get("id").toString()))
                            ), ArrayList::new
                    )
            );
       */
/* for (Role role:roles){
            List<Menu> menu=menuDao.getMainMenuList(role.getId());
            if (!menus.containsAll(menu)){
                menus.addAll(menu);
            }
        }*//*

            ResponseMsgUtil.sendSuccessMsg("查询成功", list, response);
        }
*/

/*
        //获取子菜单
        public Map<String, Object> buildSubmenu (Map < String, Object > parentMenu,int role_id){
            List<Map<String, Object>> secondMenu = menuDao.getSecondMenu((int) parentMenu.get("id"), role_id);
            Map<String, Object> map = new HashMap<>(parentMenu);
            for (int i = 0; i < secondMenu.size(); i++) {
                Map<String, Object> temp = new HashMap<>(secondMenu.get(i));
                if (menuDao.checkContainSubmenu((int) temp.get("id")) > 0) {
                    temp.put("submenu", menuDao.getSecondMenu((int) temp.get("id"), role_id));
                    secondMenu.set(i, temp);
                }
            }
            map.put("submenu", secondMenu);
            return map;
        }
*/

    /**
     * 获取角色列表
     *
     * @param page     页码
     * @param size     每页数量
     * @param request  请求
     * @param response 响应
     */
    public void getRoleList(int page, int size, HttpServletRequest request, HttpServletResponse response) {
/*
            PageRequest pageRequest = PageRequest.of((page - 1), size);
*/
        try {
            ResponseMsgUtil.sendSuccessMsg("ok", roleDao.findAll(), response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 刷新令牌
     */
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) {
        String token = request.getHeader(jwtTokenUtil.getHeader());
        if (StringUtils.hasLength(token) && !token.equals("null")) {
            String username = jwtTokenUtil.getUsernameIgnoreExpiration(token);
            //如果jwt过期，则获取refresh_token，判断refresh_token是否过期，不过期则刷新token返回前端
            String refreshToken = refreshTokenDao.getRefreshToken(username);
            UserDetails userDetails = loadUserByUsername(username);
            if (jwtTokenUtil.validateToken(refreshToken, userDetails)) {
                try {
                    String newToken = jwtTokenUtil.refreshToken(token);
                    ResponseMsgUtil.sendSuccessMsg("刷新jwt", newToken, response);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    ResponseMsgUtil.sendFailMsg("登录状态过期请重新登录", response);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            try {
                ResponseMsgUtil.sendFailMsg("登录状态过期请重新登录", response);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}


