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
@TableName("promotions")
public class Promotions implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "promotion_id", type = IdType.AUTO)
    private Integer promotion_id;

    @TableField("product_id")
    private Integer product_id;

    @TableField("promotion_name")
    private String promotion_name;

    @TableField("start_time")
    private LocalDateTime start_time;

    @TableField("end_time")
    private LocalDateTime end_time;

    @TableField("discount_rate")
    private Double discount_rate;


}
