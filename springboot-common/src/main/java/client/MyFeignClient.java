package client;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author y25958
 */
@FeignClient(name = "myFeignClient",url = "http://127.0.0.1:8701",configuration = OkHttpFeignConfiguration.class)
public interface MyFeignClient {

    @PostMapping
    String executeCommand(@RequestBody String params);
}
