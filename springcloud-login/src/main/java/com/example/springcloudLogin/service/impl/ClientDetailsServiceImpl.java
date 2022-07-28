package com.example.springcloudLogin.service.impl;

import com.example.springcloudLogin.entity.OauthClientEntity;
import com.example.springcloudLogin.mapper.OauthClientMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

@Service
@RequiredArgsConstructor
public class ClientDetailsServiceImpl implements ClientDetailsService {
    @Autowired
    private OauthClientMapper oauthClientMapper;
    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        try {
            OauthClientEntity oauthClient = oauthClientMapper.selectById(clientId);
            Assert.isTrue(oauthClient != null, "OAuth2 客户端不存在");
            if (!ObjectUtils.isEmpty(oauthClient)) {
                BaseClientDetails clientDetails = new BaseClientDetails(
                        oauthClient.getClientId(),
                        oauthClient.getResourceIds(),
                        oauthClient.getScope(),
                        oauthClient.getAuthorizedGrantTypes(),
                        oauthClient.getAuthorities(),
                        oauthClient.getWebServerRedirectUri()
                );
                clientDetails.setClientSecret(oauthClient.getClientSecret());
                clientDetails.setAccessTokenValiditySeconds(oauthClient.getAccessTokenValidity());
                clientDetails.setRefreshTokenValiditySeconds(oauthClient.getRefreshTokenValidity());
                return clientDetails;
            } else {
                throw new NoSuchClientException("No client with requested id: " + clientId);
            }
        } catch (EmptyResultDataAccessException var4) {
            throw new NoSuchClientException("No client with requested id: " + clientId);
        }
    }
}
