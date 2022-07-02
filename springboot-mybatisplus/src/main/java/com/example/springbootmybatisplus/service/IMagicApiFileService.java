package com.example.springbootmybatisplus.service;

import com.example.springbootmybatisplus.entity.MagicApiFile;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sun.xml.internal.ws.util.CompletedFuture;

import java.util.Optional;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yzg
 * @since 2022-07-02
 */
public interface IMagicApiFileService extends IService<MagicApiFile> {

    CompletedFuture<Optional<MagicApiFile>> getAll();
}
