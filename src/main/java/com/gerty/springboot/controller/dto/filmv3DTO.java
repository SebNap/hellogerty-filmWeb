package com.gerty.springboot.controller.dto;

import lombok.Data;

/**
 * 接受前端登录请求的参数
 */

@Data
public class filmv3DTO {
    private String movieID;
    private String imageURL;
    private String title;
    private String voteAverage;
    private String voteCount;
}
