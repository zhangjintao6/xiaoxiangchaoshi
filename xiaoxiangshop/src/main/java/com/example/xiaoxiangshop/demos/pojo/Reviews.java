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
@TableName("reviews")
public class Reviews implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "review_id", type = IdType.AUTO)
    private Integer review_id;

    @TableField("user_id")
    private Integer user_id;

    @TableField("product_id")
    private Integer product_id;

    @TableField("content")
    private String content;

    @TableField("rating")
    private Integer rating;

    @TableField("create_time")
    private LocalDateTime create_time;


}
