package com.gerty.springboot.controller.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 接受前端登录请求的参数
 */

@Data
public class commentv1DTO {

    private Integer id;

    private Integer uid;

    private String content;

    private String nickname;

    private LocalDateTime time;

    private String movieID;

    private String headUrl;
}