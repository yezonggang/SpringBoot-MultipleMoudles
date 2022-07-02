package com.example.springbootmybatisplus.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.sql.Clob;
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
 * @since 2022-07-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("MAGIC_API_FILE")
@ApiModel(value="MagicApiFile对象", description="")
public class MagicApiFile implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "FILE_PATH", type = IdType.AUTO)
    private String filePath;

    @TableField("FILE_CONTENT")
    private Clob fileContent;


}
