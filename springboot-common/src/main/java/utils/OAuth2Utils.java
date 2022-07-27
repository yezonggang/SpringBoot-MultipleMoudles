package utils;

import constant.SecurityConstants;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * 请求工具类
 *
 * @author haoxr
 * @date 2022/2/12
 */
@Slf4j
public class OAuth2Utils {

    /**
     * 获取登录认证的客户端ID
     * 方式二：放在请求头（Request Headers）中的Authorization字段，且经过加密，例如 Basic Y2xpZW50OnNlY3JldA== 明文等于 client:secret
     *
     * @return
     */
    @SneakyThrows
    public static String getOAuth2ClientId() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        // 从请求路径中获取
        String clientId = request.getParameter(SecurityConstants.ADMIN_CLIENT_ID);
        if (!ObjectUtils.isEmpty(clientId)) {
            return clientId;
        }

        // 从请求头获取
        String basic = request.getHeader(SecurityConstants.AUTHORIZATION_KEY);
        if (StringUtils.isNotBlank(basic) && basic.startsWith(SecurityConstants.BASIC_PREFIX)) {
            basic = basic.split(SecurityConstants.BASIC_PREFIX)[1].trim();
            clientId = new String(Base64.getDecoder().decode(basic.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
        }
        return clientId;
    }

    /**
     * 解析JWT获取获取认证身份标识
     *
     * @return
     */
/*    @SneakyThrows
    public static String getAuthenticationIdentity() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String refreshToken = request.getParameter(SecurityConstants.REFRESH_TOKEN_KEY);

        JWTParser jwtParser = new JWTParser();
        Payload payload = jwtParser.parsePayload(refreshToken);


        String authenticationIdentity = jsonObject.getStr(SecurityConstants.AUTHENTICATION_IDENTITY_KEY);
        if (StrUtil.isBlank(authenticationIdentity)) {
            authenticationIdentity = AuthenticationIdentityEnum.USERNAME.getValue();
        }
        return authenticationIdentity;
    }*/
}
