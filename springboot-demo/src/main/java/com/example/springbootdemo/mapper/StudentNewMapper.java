package com.example.springbootdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springbootdemo.model.entitiy.StudentNew;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudentNewMapper extends BaseMapper<StudentNew> {

}