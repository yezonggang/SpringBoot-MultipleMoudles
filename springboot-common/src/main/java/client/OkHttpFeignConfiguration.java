package client;

import executor.CustomThreadPool;
import feign.Feign;
import feign.Logger;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * @author y25958
 */
@Configuration
@ConditionalOnClass(Feign.class)
@AutoConfigureBefore(FeignAutoConfiguration.class)
public class OkHttpFeignConfiguration implements RequestInterceptor {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(OkHttpFeignConfiguration.class);


    @Bean(name = "myCustomOkHttpClient")
    public OkHttpClient okHttpClient(){
        return new OkHttpClient.Builder()
                .readTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout((10), TimeUnit.SECONDS)
                .connectionPool(new ConnectionPool(20, 10L, TimeUnit.MINUTES))
                .build();
    }


    @Bean
    Logger.Level feignLoggerLever(){return Logger.Level.FULL;}


    @Override
    public void apply(RequestTemplate template) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if(!ObjectUtils.isEmpty(servletRequestAttributes)){
            HttpServletRequest request = servletRequestAttributes.getRequest();
            String token = request.getHeader("token");
            if(StringUtils.hasText(token)){
                template.header("token", token);
            }
            template.header("service", "test");
        }

    }
}
