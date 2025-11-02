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
@TableName("t_like")
public class T_like implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "like_id", type = IdType.AUTO)
    private Integer like_id;

    @TableField("label_id")
    private Integer label_id;

    @TableField("user_id")
    private Integer user_id;

    @TableField("weight")
    private Integer weight;


}
