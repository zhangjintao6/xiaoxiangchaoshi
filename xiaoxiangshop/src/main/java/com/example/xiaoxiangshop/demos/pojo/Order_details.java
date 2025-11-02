package com.example.xiaoxiangshop.demos.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

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
@TableName("order_details")
public class Order_details implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "order_detail_id", type = IdType.AUTO)
    private Integer order_detail_id;

    @TableField("order_id")
    private Long order_id;

    @TableField("product_id")
    private Integer product_id;

    @TableField("quantity")
    private Integer quantity;

    @TableField("unti_price")
    private Double unti_price;

    @TableField("total_price")
    private Double total_price;

    @TableField(exist = false)
    private Products products;


}
