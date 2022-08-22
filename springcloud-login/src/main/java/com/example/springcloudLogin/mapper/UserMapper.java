package com.example.springcloudLogin.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springcloudLogin.entity.UserEntity;
import com.example.springcloudLogin.vo.UserEntityVO;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yzg
 * @since 2022-07-17
 */
@Mapper
public interface UserMapper extends MPJBaseMapper<UserEntity> {
    IPage<UserEntity> selectPageVo(Page page);
}
