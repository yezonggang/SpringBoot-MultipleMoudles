package com.example.springcloudLogin.config;

import com.example.springcloudLogin.service.impl.ClientDetailsServiceImpl;
import com.example.springcloudLogin.vo.SecurityUser;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.security.KeyPair;
import java.util.*;


@AllArgsConstructor
@Configuration
@EnableAuthorizationServer
public class Oauth2ServerConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private  AuthenticationManager authenticationManager;
    @Autowired
    private  ClientDetailsServiceImpl clientDetailsService;
    @Autowired
    private UserDetailsService userDetailsService;
    /**
     * 客户端配置
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
/*        *//**
         *
         *简单使用，存放在内存中
         * @param clientsDetails
         * @throws Exception
         */
/*        clients.inMemory()
                .withClient("client-app")
                .secret(passwordEncoder.encode("123456"))
                .scopes("all")
                .authorizedGrantTypes("password")
                .accessTokenValiditySeconds(3600)
                .refreshTokenValiditySeconds(86400);*/
        /**
         * 授权用户存放在数据库中
         */
        clients.withClientDetails(clientDetailsService);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // 将自定义的授权类型添加到tokenGranters中
        List<TokenGranter> tokenGranters = new ArrayList<>(Collections.singletonList(endpoints.getTokenGranter()));
        // 添加手机短信验证码授权模式的授权者
        tokenGranters.add(new LoginTokenGranter(endpoints.getTokenServices(), endpoints.getClientDetailsService(),
                endpoints.getOAuth2RequestFactory(), authenticationManager));


        endpoints
                //设置异常WebResponseExceptionTranslator，用于处理用户名，密码错误、授权类型不正确的异常
                //.exceptionTranslator(new OAuthServerWebResponseExceptionTranslator())
                //授权码模式所需要的authorizationCodeServices
                //.authorizationCodeServices(authorizationCodeServices())
                //密码模式所需要的authenticationManager
                .authenticationManager(authenticationManager)
                //令牌管理服务，无论哪种模式都需要
                .tokenServices(tokenServices())
                .userDetailsService(userDetailsService)
                //添加进入tokenGranter
                .tokenGranter(new CompositeTokenGranter(tokenGranters))
                //只允许POST提交访问令牌，uri：/oauth/token
                .allowedTokenEndpointRequestMethods(HttpMethod.POST);
    }

    /**
     * 允许表单认证
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.allowFormAuthenticationForClients();
    }


    /**
     * 令牌管理服务的配置
     */
    @Bean
    public DefaultTokenServices tokenServices() {

        DefaultTokenServices tokenServices = new DefaultTokenServices();
        //令牌服务
        tokenServices.setTokenStore(tokenStore());
        //支持令牌的刷新
        tokenServices.setSupportRefreshToken(true);
        //客户端端配置策略
        tokenServices.setClientDetailsService(clientDetailsService);
        //access_token的过期时间
        tokenServices.setAccessTokenValiditySeconds(60 * 60 * 24 * 3);
        //refresh_token的过期时间
        tokenServices.setRefreshTokenValiditySeconds(60 * 60 * 24 * 3);
        //设置令牌增强，使用JwtAccessTokenConverter进行转换
        tokenServices.setTokenEnhancer(accessTokenConverter());

        return tokenServices;

    }
    /**
     * JWT内容增强
     */
    @Bean
    public TokenEnhancer tokenEnhancer() {
        TokenEnhancer tokenEnhancer = (accessToken, authentication) -> {
            Map<String, Object> additionalInfo = new HashMap<>();
            Object principal = authentication.getUserAuthentication().getPrincipal();
            if (principal instanceof SecurityUser) {
                SecurityUser sysUserDetails = (SecurityUser) principal;
                additionalInfo.put("userId", sysUserDetails.getId());
                additionalInfo.put("username", sysUserDetails.getUsername());
            }
            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
            return accessToken;
        };
        return tokenEnhancer;
    }

    /**
     * 令牌的存储策略
     */
    @Bean
    public TokenStore tokenStore() {
        //使用JwtTokenStore生成JWT令牌
        return new JwtTokenStore(accessTokenConverter());
    }

    /**
     * 使用非对称加密算法对token签名
     */
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setKeyPair(keyPair());
        return jwtAccessTokenConverter;
    }
    /**
     * 密钥库中获取密钥对(公钥+私钥)
     */
    @Bean
    public KeyPair keyPair() {
        //从classpath下的证书中获取秘钥对
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("jwt.jks"), "spring-cloud".toCharArray());
        return keyStoreKeyFactory.getKeyPair("springcloud", "spring-cloud".toCharArray());
    }

}