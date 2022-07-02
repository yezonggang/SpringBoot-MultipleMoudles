package com.example.springbootmybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springbootmybatisplus.entity.MagicApiFile;
import com.example.springbootmybatisplus.mapper.MagicApiFileMapper;
import com.example.springbootmybatisplus.service.IMagicApiFileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springbootmybatisplus.utils.Either;
import com.sun.xml.internal.ws.util.CompletedFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yzg
 * @since 2022-07-02
 */
@Service
public class MagicApiFileServiceImpl extends ServiceImpl<MagicApiFileMapper, MagicApiFile> implements IMagicApiFileService {


    @Override
    public CompletableFuture<Either> getAll() {
        QueryWrapper<MagicApiFile> queryWrapper = new QueryWrapper();
        return CompletableFuture.completedFuture(Either.Left("xxx"));
    }
}
