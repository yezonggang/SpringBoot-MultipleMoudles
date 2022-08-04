package client;

import feign.Contract;
import feign.Feign;
import feign.Logger;
import feign.Request;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author y25958
 */
@Component
@AutoConfigureAfter(OkHttpFeignConfiguration.class)
public class CustomFeignClientFactory {
    private static final Contract CONTRACT = new SpringMvcContract();
    private static final Request.Options OPTIONS = new Request.Options(8
            , TimeUnit.SECONDS
            , 60
            , TimeUnit.SECONDS
            , true);


    @Value("12121")
    private String PORT;

    @Resource
    private okhttp3.OkHttpClient myCustomOkHttpClient;

    public <T> T createFeignClient(Class<T> clazz,String ip){
        return Feign.builder().logger(new Slf4jLogger(clazz))
                .logLevel(Logger.Level.FULL)
                //契约配置
                .contract(CONTRACT)
                //超时设置
                .options(OPTIONS)
                .client(new OkHttpClient(myCustomOkHttpClient))
                .target(clazz, getUrl(ip));
    }

    private String getUrl(String ip){
        return String.format("http://%s:%s", ip, PORT);
    }

}
