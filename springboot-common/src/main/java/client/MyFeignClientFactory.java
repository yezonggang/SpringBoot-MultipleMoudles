package client;

import feign.Contract;
import feign.Feign;
import feign.Logger;
import feign.Request;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

/**
 * @author y25958
 */
@Component
@AutoConfigureAfter(OkHttpFeignConfiguration.class)
public class MyFeignClientFactory {
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
                .contract(CONTRACT)
                .options(OPTIONS)
                .client(new OkHttpClient(myCustomOkHttpClient))
                .target(clazz, getUrl(ip));
    }

    private String getUrl(String ip){
        return String.format("http://%s:%s", ip, PORT);
    }

}
