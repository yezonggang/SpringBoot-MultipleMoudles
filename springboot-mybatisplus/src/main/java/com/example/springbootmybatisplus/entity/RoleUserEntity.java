package com.example.springbootmybatisplus.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author yzg
 * @since 2022-07-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="RoleUser对象", description="")
@TableName("role_user")
public class RoleUserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long userId;

    private Long roleId;


}
