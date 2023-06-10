package com.gerty.springboot.controller.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 接受前端登录请求的参数
 */

@Data
public class likev1DTO {

    private Integer id;

    private String movieID;

    private String imageURL;

    private String title;

    private String voteAverage;

    private String voteCount;

    private String nickname;
}