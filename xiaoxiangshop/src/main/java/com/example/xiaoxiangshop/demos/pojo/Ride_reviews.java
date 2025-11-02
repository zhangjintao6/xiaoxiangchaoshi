package com.example.xiaoxiangshop.demos.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author zjt
 * @since 2024-09-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ride_reviews")
public class Ride_reviews implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ride_reviews_id", type = IdType.AUTO)
    private Integer ride_reviews_id;

    @TableField("ride_id")
    private Integer ride_id;

    @TableField("order_id")
    private Integer order_id;

    @TableField("content")
    private String content;

    @TableField("rating")
    private Integer rating;

    @TableField("create_time")
    private LocalDateTime create_time;


}
