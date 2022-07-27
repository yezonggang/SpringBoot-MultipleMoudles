package client;

/**
 * @author y25958
 */
public class TestFeign {

    public  static CustomFeignClientFactory customFeignClientFactory;
    public  static  MyFeignClient myFeignClient;

    public static void main(String[] args) {
        String testBody = "body";
        
        // 根据ip或者port实例化feign调用
        CustomFeignClient customFeignClient = customFeignClientFactory.createFeignClient(CustomFeignClient.class, "127.0.0.1");
        customFeignClient.executeCommand(testBody);

        // 直接传参调用
        myFeignClient.executeCommand(testBody);
    }



}
