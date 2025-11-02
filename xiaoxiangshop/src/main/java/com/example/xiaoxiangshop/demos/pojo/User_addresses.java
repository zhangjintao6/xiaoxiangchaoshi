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
@TableName("user_addresses")
public class User_addresses implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "adress_id", type = IdType.AUTO)
    private Integer adress_id;

    @TableField("user_id")
    private Integer user_id;

    @TableField("address_name")
    private String address_name;

    @TableField("province")
    private String province;

    @TableField("city")
    private String city;

    @TableField("county")
    private String county;

    @TableField("other_name")
    private String other_name;

    @TableField("other_sex")
    private String other_sex;

    @TableField("other_phone")
    private String other_phone;

    @TableField("default_address")
    private String default_address;



}
