package com.example.springbootmybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springbootmybatisplus.entity.StudentEntity;
import com.example.springbootmybatisplus.mapper.StudentMapper;
import com.example.springbootmybatisplus.service.IStudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springbootmybatisplus.utils.ApiError;
import com.example.springbootmybatisplus.utils.ApiErrorEnum;
import com.example.springbootmybatisplus.utils.Either;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author yzg
 * @since 2022-07-02
 */
@Service
@Slf4j
public class StudentServiceImpl extends ServiceImpl<StudentMapper, StudentEntity> implements IStudentService {
    @Autowired
    private StudentMapper studentMapper;


    @Override
    public CompletableFuture<Either<ApiError, List<StudentEntity>>> getAll()  {
        log.info("xxxxxxxxxxxx--begin--xxxxxxx");
        LambdaQueryWrapper<StudentEntity> queryWrapper = new QueryWrapper<StudentEntity>().lambda().eq(StudentEntity::getName,"yzg");
        Optional<List<StudentEntity>> optionalStudents = Optional.ofNullable(studentMapper.selectList(queryWrapper));
        // 非空判断,只要sql对mybatis不会给null，mybatis内部错误会传null
        if (optionalStudents.isPresent()) {
            log.info("is not null.");
            List<StudentEntity> listStudents =  optionalStudents.get().stream().filter(student -> student.getGender().equalsIgnoreCase("boy")).collect(Collectors.toList());
            return CompletableFuture.completedFuture(Either.Right(listStudents));
        } else {
            return CompletableFuture.completedFuture(Either.Left(ApiError.from(ApiErrorEnum.CHECK_DATABASE_WRONG)));
        }
    }
}
