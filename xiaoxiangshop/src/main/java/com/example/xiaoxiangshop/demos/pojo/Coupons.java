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
@TableName("coupons")
public class Coupons implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "coupon_id", type = IdType.AUTO)
    private Integer coupon_id;

    @TableField("user_id")
    private Integer user_id;

    @TableField("coupon_amount")
    private Double coupon_amount;

    @TableField("threshold_amount")
    private Double threshold_amount;

    @TableField("coupon_rate")
    private Double coupon_rate;

    @TableField("expiration_time")
    private LocalDateTime expiration_time;


}
