package com.example.xiaoxiangshop.demos.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
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
@TableName("product_types")
public class Product_types implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "type_id", type = IdType.AUTO)
    private Integer type_id;

    @TableField("type_name")
    private String type_name;

    @TableField("is_parent")
    private Integer is_parent;

    @TableField("parent_id")
    private Integer parent_id;

    @TableField("img")
    private String img;

    @TableField(exist = false)
    private Product_types parentType;

    @TableField(exist = false)
    private List<Product_types> childType;

    @TableField(exist = false)
    private List<Products> products;
}
