package com.example.springbootdemo.model.entitiy;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Data
// 如果数据库中的表名称和pojo类名称不对应，使用注解修改
@TableName("STUDENT")
public class StudentNew {
    public int ID;
    public String NAME;
    public int AGE;
    public String GENDER;
    public String CLASS;
}
