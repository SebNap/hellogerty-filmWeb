package com.gerty.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
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
 * @since 2023-06-09
 */
@Getter
@Setter
  @TableName("commentv1")
@ApiModel(value = "Commentv1对象", description = "")
public class Commentv1 implements Serializable {

    private static final long serialVersionUID = 1L;

    private String content;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

    private LocalDateTime time;

    private String movieID;

    private String nickname;

    private String headUrl;

    private Integer uid;


}
