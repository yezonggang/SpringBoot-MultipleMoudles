package com.example.springcloudgateway.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/*
 * 网关白名单配置
 */

@AllArgsConstructor
@Component
@ConfigurationProperties(prefix = "white")
public class WhiteListConfig {
    private List<String> urls;

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    @Override
    public String toString() {
        return "WhiteListConfig{" +
                "urls=" + urls +
                '}';
    }
}
