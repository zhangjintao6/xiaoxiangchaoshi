package com.example.xiaoxiangshop.demos.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * VIEW
 * </p>
 *
 * @author zjt
 * @since 2024-09-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("yanxuan_view")
public class Yanxuan_view implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("product_id")
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


}
