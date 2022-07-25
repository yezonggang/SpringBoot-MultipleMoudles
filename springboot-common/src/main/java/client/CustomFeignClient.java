package client;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author y25958
 */
public interface CustomFeignClient {
    @PostMapping("get_user")
    <T> String executeCommand(@RequestBody T params);
}
