package com.example.springbootmybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springbootmybatisplus.entity.Student;
import com.example.springbootmybatisplus.mapper.StudentMapper;
import com.example.springbootmybatisplus.service.IStudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springbootmybatisplus.utils.ApiError;
import com.example.springbootmybatisplus.utils.ApiErrorEnum;
import com.example.springbootmybatisplus.utils.Either;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author yzg
 * @since 2022-07-02
 */
@Service

public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {
    @Autowired
    StudentMapper studentMapper;
    @Override
    public CompletableFuture<Either<ApiError, List<Student>>> getAll() {
        LambdaQueryWrapper<Student> queryWrapper = new QueryWrapper<Student>().lambda().eq(Student::getName,"yzg");
        List<Student> studentList = studentMapper.selectList(queryWrapper);

        return CompletableFuture.completedFuture(Either.Left(ApiError.from(ApiErrorEnum.CHECK_DATABASE_WRONG)));
    }
}
