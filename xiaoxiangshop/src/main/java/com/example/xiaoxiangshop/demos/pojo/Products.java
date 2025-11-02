package com.example.xiaoxiangshop.demos.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * @since 2024-09-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("products")
public class Products implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "product_id", type = IdType.AUTO)
    private Integer product_id;

    @TableField("product_name")
    private String product_name;

    @TableField("description")
    private String description;

    @TableField("price")
    private Double price;

    @TableField("sale_num")
    private Integer sale_num;

    @TableField("stock")
    private Integer stock;

    @TableField("brand_id")
    private Integer brand_id;

    @TableField("type_id")
    private Integer type_id;

    @TableField("create_time")
    private LocalDateTime create_time;

    @TableField("img")
    private String img;

    @TableField("img_detail")
    private Integer img_detail;

    @TableField(exist = false)
    private List<Label> productLabels;

    @TableField(exist = false)
    private Product_types types;

    @TableField(exist = false)
    private Product_brand brand;

    @TableField(exist = false)
    private List<Promotions> promotions;

}
