package com.example.xiaoxiangshop.demos.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.xiaoxiangshop.demos.pojo.User_addresses;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zjt
 * @since 2024-09-12
 */
public interface IUser_addressesDao extends BaseMapper<User_addresses> {
    //添加地址信息
    void addressAdd(User_addresses addresses);
    //修改地址信息
    void addressEdit();


    //设置默认值
    void setDefaultAddress();
}
