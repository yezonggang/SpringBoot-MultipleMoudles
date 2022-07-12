package com.example.springbootmybatisplus.service;

import com.example.springbootmybatisplus.entity.StudentEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springbootmybatisplus.utils.ApiError;
import com.example.springbootmybatisplus.utils.Either;

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
