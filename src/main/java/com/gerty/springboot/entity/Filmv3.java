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
 * @since 2023-06-06
 */
@Getter
@Setter
  @TableName("filmv3")
@ApiModel(value = "Filmv3对象", description = "")
public class Filmv3 implements Serializable {

    private static final long serialVersionUID = 1L;

    private String movieID;

    private String imageURL;

    private String title;

    private String voteAverage;

    private String voteCount;


}
