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
@TableName("label")
public class Label implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "label_id", type = IdType.AUTO)
    private Integer label_id;

    @TableField("label_name")
    private String label_name;

    @TableField("img")
    private String img;

}
