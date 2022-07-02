package com.example.springbootmybatisplus.service.impl;

import com.example.springbootmybatisplus.entity.MagicApiFile;
import com.example.springbootmybatisplus.mapper.MagicApiFileMapper;
import com.example.springbootmybatisplus.service.IMagicApiFileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sun.xml.internal.ws.util.CompletedFuture;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
    public CompletedFuture<Optional<MagicApiFile>> getAll() {
        return null;
    }
}
