package com.example.springbootmybatisplus.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author yzg
 * @since 2022-07-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ACCOUNT_STATE")
@ApiModel(value="AccountState对象", description="")
public class AccountStateEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("USER_ID")
    private String userId;

    @TableField("ID")
    private Integer id;

    @TableField("ACCOUNT_NON_EXPIRED")
    private Integer accountNonExpired;

    @TableField("ACCOUNT_NON_LOCKED")
    private Integer accountNonLocked;


    @TableField("CREDENTIALS_NON_EXPIRED")
    private Integer credentialsNonExpired;

    @TableField("ENABLED")
    private Integer enabled;


}
