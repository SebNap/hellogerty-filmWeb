package com.gerty.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import nonapi.io.github.classgraph.json.Id;

/**
 * <p>
 * 
 * </p>
 *
 * @author SebNap(wyq)
 * @since 2023-06-10
 */
@Getter
@Setter
  @TableName("filmv3")
@ApiModel(value = "Filmv3对象", description = "")
public class Filmv3 implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @TableField("movie_i_d")
    @TableId(type = IdType.INPUT)
    private String movieID;

    private String imageURL;

    private String title;

    private String voteAverage;

    private String voteCount;


}
