package com.example.xiaoxiangshop.demos.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

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
@TableName("users")
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer user_id;

    @TableField("username")
    private String username;

    @TableField("password")
    private String password;

    @TableField("sex")
    private String sex;

    @TableField("age")
    private Integer age;

    @TableField("birthday")
    private String birthday;

    @TableField("nickname")
    private String nickname;

    @TableField("phone")
    private String phone;

    @TableField("img")
    private String img;

    @TableField("state_id")
    private Integer state_id;

    @TableField("account")
    private Double account;

    @TableField("integeral")
    private Integer integeral;

    @TableField("registration_time")
    private LocalDateTime registration_time;

    @TableField("last_login_time")
    private LocalDateTime last_login_time;

    @TableField("role_id")
    private Integer role_id;

    @TableField(exist = false)
    private Role role;


}
