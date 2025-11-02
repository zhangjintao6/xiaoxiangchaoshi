package com.example.xiaoxiangshop.demos.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.xiaoxiangshop.demos.dao.ICartsDao;
import com.example.xiaoxiangshop.demos.pojo.Carts;
import com.example.xiaoxiangshop.demos.service.ICartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zjt
 * @since 2024-09-12
 */
@Service
public class CartsServiceImpl extends ServiceImpl<ICartsDao, Carts> implements ICartsService {

    @Autowired
    ICartsDao cartsDao;

    @Override
    public void addCart(Carts carts) {
        cartsDao.addCart(carts);
    }

    @Override
    public List<Carts> findCartsByUserId(int id) {
        return cartsDao.findCartsByUserId(id);
    }

    @Override
    public void update(Carts carts) {
        cartsDao.update(carts);
    }

    @Override
    public void delCartByUserid(int id) {
        cartsDao.delCartByUserid(id);
    }

    @Override
    public void delCartByPid(int id) {
        cartsDao.delCartByPid(id);
    }

    @Override
    public List<Carts> findCartByUserid(int id) {
        return cartsDao.findCartByUserid(id);
    }


}
