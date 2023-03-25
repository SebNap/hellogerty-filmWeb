package com.gerty.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@NoArgsConstructor
//@AllArgsConstructor
@TableName(value = "wyq_user")

public class wyqUser {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String username;
    @JsonIgnore //忽略某个字段 不展示给前端
    private String password;
    private String nickname;
    private String email;
    private String phone;
    private String address;

}
