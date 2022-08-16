/*
package com.example.springcloudLogin.fegin;

import feign.hystrix.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "terminal-device", path = "/auth", fallbackFactory = FallbackFactory.class)
public interface LoginFeignClient {

    @RequestMapping(value = "/v2/device/deviceInfo", method = RequestMethod.POST,consumes = "application/json")
    Rex<DeviceInfoOutVo> deviceInfo(@RequestBody DeviceOutDto deviceOutDto) throws Exception;
}
*/
