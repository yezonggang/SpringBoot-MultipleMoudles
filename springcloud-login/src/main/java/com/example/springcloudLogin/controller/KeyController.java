package com.example.springcloudLogin.controller;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;

@RestController
@AllArgsConstructor
public class KeyController {
    private KeyPair keyPair;
    @RequestMapping(value = "/rsa/publicKey",method = RequestMethod.GET)
    public Map<String,Object> getKey(){
        RSAKey key = new RSAKey.Builder((RSAPublicKey) keyPair.getPublic()).build();
        return  new JWKSet(key).toJSONObject();
    }
}
