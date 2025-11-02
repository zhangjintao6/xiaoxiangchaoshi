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
@TableName("img_detail")
public class Img_detail implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "img_detail_id", type = IdType.AUTO)
    private Integer img_detail_id;

    @TableField("product_id")
    private Integer product_id;

    @TableField("img")
    private String img;


}
