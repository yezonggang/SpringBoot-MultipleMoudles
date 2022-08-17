package utils;

import com.alibaba.fastjson.JSONObject;
import constant.SecurityConstants;
import lombok.SneakyThrows;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class JwtUtils {

    @SneakyThrows
    public static JSONObject getJwtPayload() {
        JSONObject jsonObject = null;
        String payload = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader(SecurityConstants.JWT_PAYLOAD_KEY);
        if (!ObjectUtils.isEmpty(payload)) {
            jsonObject = JSONObject.parseObject(payload);
        }
        return jsonObject;
    }
}