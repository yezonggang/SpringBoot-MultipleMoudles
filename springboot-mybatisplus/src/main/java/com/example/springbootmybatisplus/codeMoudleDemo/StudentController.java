package com.example.springbootmybatisplus.codeMoudleDemo;


import com.example.springbootmybatisplus.utils.ResponseData;
import io.swagger.annotations.Api;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.concurrent.CompletableFuture;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author yzg
 * @since 2022-07-02
 */
@RestController
@RequestMapping("/student")
@Api("这是测试方法")
public class StudentController {
    @Autowired
    StudentServiceImpl studentService;

    @GetMapping("/get")
    @Test
    public CompletableFuture<ResponseData> getAll() {
        return CompletableFuture.completedFuture(ResponseData.success("xxxxxxxxx"));
       /* return studentService.getAll().thenCompose(resp ->
                resp.left.map(apiError -> CompletableFuture.completedFuture(ResponseData.fail(apiError)))
                        .orElseGet(() -> CompletableFuture.completedFuture(ResponseData.success(resp.right))));
    */}
}
