package com.example.xiaoxiangshop.demos.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.xiaoxiangshop.demos.pojo.User_addresses;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zjt
 * @since 2024-09-12
 */
public interface IUser_addressesService extends IService<User_addresses> {

    //添加地址信息
    void addressAdd(User_addresses addresses);
    //修改地址信息
    void addressEdit();

    //设置默认值
    void setDefaultAddress();
}
