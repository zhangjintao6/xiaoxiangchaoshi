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
 * @since 2024-09-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("product_brand")
public class Product_brand implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "brand_id", type = IdType.AUTO)
    private Integer brand_id;

    @TableField("brand_name")
    private String brand_name;


}
