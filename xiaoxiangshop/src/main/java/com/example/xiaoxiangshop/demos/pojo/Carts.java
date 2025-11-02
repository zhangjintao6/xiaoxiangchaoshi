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
@TableName("carts")
public class Carts implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "cart_id", type = IdType.AUTO)
    private Integer cart_id;

    @TableField("user_id")
    private Integer user_id;

    @TableField("product_id")
    private Integer product_id;

    @TableField("quantity")
    private Integer quantity;

    @TableField(exist = false)
    private Products products;
}
