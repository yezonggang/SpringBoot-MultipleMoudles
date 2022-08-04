package constant;

import org.apache.tomcat.util.descriptor.web.SecurityConstraint;

public class SecurityConstants extends SecurityConstraint {
    public static final String AUTHORIZATION_KEY = "Authorization";
    public static final String BASIC_PREFIX = "Basic";
    public static final String ADMIN_CLIENT_ID = "client-app";
    /**
     * JWT载体key
     */
    public static final String JWT_PAYLOAD_KEY = "payload";
    /**
     * JWT令牌前缀
     */
    public static final String JWT_PREFIX = "Bearer ";
    public static final String STRING_NULL = "";
    /**
     * JWT ID 唯一标识
     */
    public static final String JWT_JTI = "jti";
    /**
     * JWT ID 唯一标识
     */
    public static final String JWT_EXP = "exp";
    /**
     * JWT存储权限前缀
     */
    public static final String AUTHORITY_PREFIX = "ROLE_";

    /**
     * JWT存储权限属性
     */
    public static final String JWT_AUTHORITIES_KEY = "authorities";

    public static final String USER_ID="userId";

    public final static String PRINCIPAL_NAME="principal";

}
