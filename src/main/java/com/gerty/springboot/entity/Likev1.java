package com.gerty.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2023-06-10
 */
@Getter
@Setter
  @TableName("likev1")
@ApiModel(value = "Likev1对象", description = "")
public class Likev1 implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

    private String movieID;

    private String imageURL;

    private String title;

    private String voteAverage;

    private String voteCount;

    private String nickname;


}
