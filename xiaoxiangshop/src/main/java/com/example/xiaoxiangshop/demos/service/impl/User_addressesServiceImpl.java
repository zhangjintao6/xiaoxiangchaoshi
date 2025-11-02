package com.example.xiaoxiangshop.demos.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.xiaoxiangshop.demos.dao.IUser_addressesDao;
import com.example.xiaoxiangshop.demos.pojo.User_addresses;
import com.example.xiaoxiangshop.demos.service.IUser_addressesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zjt
 * @since 2024-09-12
 */
@Service
public class User_addressesServiceImpl extends ServiceImpl<IUser_addressesDao, User_addresses> implements IUser_addressesService {


    @Autowired
    IUser_addressesDao addressesDao;
    @Override
    public void addressAdd(User_addresses addresses) {
        addressesDao.addressAdd(addresses);
    }

    @Override
    public void addressEdit() {
        addressesDao.addressEdit();
    }

    @Override
    public void setDefaultAddress() {
        addressesDao.setDefaultAddress();
    }
}
