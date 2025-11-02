package com.example.xiaoxiangshop.demos.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author zjt
 * @since 2024-09-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("orders")
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "order_id")
    private Long order_id;

    @TableField("user_id")
    private Integer user_id;

    @TableField("ride_id")
    private Integer ride_id;

    @TableField("order_status_id")
    private Integer order_status_id;

    @TableField("start_time")
    private LocalDateTime start_time;

    @TableField("end_time")
    private LocalDateTime end_time;

    @TableField("total_amount")
    private Double total_amount;

    @TableField("user_note")
    private String user_note;

    @TableField("pay_method")
    private String pay_method;

    @TableField("distance")
    private Integer distance;

    @TableField("price")
    private Double price;

    @TableField(exist = false)
    private User_addresses user_addresses;

    @TableField(exist = false)
    private User_addresses addresses;

    @TableField(exist = false)
    private List<Order_details> orderDetails;

}
