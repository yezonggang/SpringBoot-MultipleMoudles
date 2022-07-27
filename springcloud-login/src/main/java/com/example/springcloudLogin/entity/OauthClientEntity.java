package com.example.springcloudLogin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 客户端实体
 *
 * @author haoxr
 * @date 2022/6/11
 */
@Data
@TableName("oauth_client_details")
public class OauthClientEntity {

    /**
     * 客户端ID
     */
    @TableId(value = "client_id",type = IdType.INPUT)
    private String clientId;

    /**
     * 客户端密钥
     */
    @TableField(value = "client_secret")
    private String clientSecret;

    /**
     * 资源id集合
     */
    @TableField(value = "resource_ids")
    private String resourceIds;

    /**
     * 作用域
     */
    @TableField(value = "scope")
    private String scope;

    /**
     * 授权方式
     */
    @TableField(value = "authorized_grant_types")
    private String authorizedGrantTypes;

    /**
     * 回调地址
     */
    @TableField(value = "web_server_redirect_uri")
    private String webServerRedirectUri;

    /**
     * 权限集合
     */
    @TableField(value = "authorities")
    private String authorities;

    /**
     * 认证令牌时效()
     */
    @TableField(value = "access_token_validity")
    private Integer accessTokenValidity;

    /**
     * 刷新令牌时效(单位:秒)
     */
    @TableField(value = "refresh_token_validity")
    private Integer refreshTokenValidity;

    /**
     * 扩展信息
     */
    @TableField(value = "additional_information")
    private String additionalInformation;

    /**
     * 是否自动放行
     */
    @TableField(value = "autoapprove")
    private String autoapprove;

}