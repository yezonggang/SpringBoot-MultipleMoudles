/*
package com.example.springcloudLogin.fegin.config;

import com.example.springcloudLogin.fegin.LoginFeignClient;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LoginFeignClientFallbackFactory implements FallbackFactory<LoginFeignClient> {
    @Override
    public LoginFeignClient create(Throwable throwable) {
        log.error("[DeviceFeignClient]服务异常:", throwable);
        return new LoginFeignClient() {

            @Override
            public SerializableResult<DeviceInfoOutVo> deviceInfo(DeviceOutDto deviceOutDto) {
                return ResultViewUtil.genErrorSerializableResult(DeviceInfoOutVo.class, Constants.ERRCODE_SERVICE_UNAVAILABLE,Constants.ERRMSG_SERVICE_UNAVAILABLE);
            }
        };
    }
}*/
