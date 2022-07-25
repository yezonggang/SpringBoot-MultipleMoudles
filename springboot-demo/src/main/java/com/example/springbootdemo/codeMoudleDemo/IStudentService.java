package com.example.springbootdemo.codeMoudleDemo;

import com.baomidou.mybatisplus.extension.service.IService;
import execption.ApiError;
import response.Either;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yzg
 * @since 2022-07-02
 */
public interface IStudentService extends IService<StudentEntity> {
    CompletableFuture<Either<ApiError,List<StudentEntity>>> getAll() throws Throwable;

}
