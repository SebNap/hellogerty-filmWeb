package com.gerty.springboot.controller.dto;

import lombok.Data;

/**
 * 接受前端登录请求的参数
 */

@Data
public class allv2DTO {
    private String adult;

    private String belongsToCollection;

    private String budget;

    private String genres;

    private String homepage;

    private String tmdbId;

    private String movieID;

    private String originalLanguage;

    private String overview;

    private String popularity;

    private String productionCompanies;

    private String productionCountries;

    private String releaseDate;

    private String revenue;

    private String runtime;

    private String spokenLanguages;

    private String status;

    private String tagline;

    private String title;

    private String video;

    private String voteAverage;

    private String voteCount;
}