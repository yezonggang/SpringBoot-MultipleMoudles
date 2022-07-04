package com.example.springbootmybatisplus.service;

import com.example.springbootmybatisplus.entity.MagicApiFile;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springbootmybatisplus.utils.Either;
import com.sun.xml.internal.ws.util.CompletedFuture;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yzg
 * @since 2022-07-02
 */
public interface IMagicApiFileService extends IService<MagicApiFile> {

    CompletableFuture<Either> getAll();
}