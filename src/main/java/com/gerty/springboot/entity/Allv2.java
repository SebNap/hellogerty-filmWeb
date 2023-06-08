package com.gerty.springboot.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author SebNap(wyq)
 * @since 2023-06-08
 */
@Getter
@Setter
  @TableName("allv2")
@ApiModel(value = "Allv2对象", description = "")
public class Allv2 implements Serializable {

    private static final long serialVersionUID = 1L;

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
