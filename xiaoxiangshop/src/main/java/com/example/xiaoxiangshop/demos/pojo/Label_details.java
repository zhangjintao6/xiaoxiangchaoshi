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
@TableName("label_details")
public class Label_details implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "label_details_id", type = IdType.AUTO)
    private Integer label_details_id;

    @TableField("product_id")
    private Integer product_id;

    @TableField("label_id")
    private Integer label_id;


}
