package com.example.xiaoxiangshop.demos.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.xiaoxiangshop.demos.dao.IOrdersDao;
import com.example.xiaoxiangshop.demos.pojo.Orders;
import com.example.xiaoxiangshop.demos.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zjt
 * @since 2024-09-19
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<IOrdersDao, Orders> implements IOrdersService {
@Autowired
IOrdersDao ordersDao;
    @Override
    public List<Orders> findOrdersByUserId(int UserId) {
        return ordersDao.findOrdersByUserId(UserId);
    }

    @Override
    public List<Orders> findOrdersByUserIdandstatus(int UserId, int Status) {
        return ordersDao.findOrdersByUserIdandstatus(UserId,Status);
    }

    @Override
    public List<Orders> ordersList() {
        return ordersDao.ordersList();
    }

    @Override
    public int ordersPick(Integer order_id) {
        return ordersDao.ordersPick(order_id);
    }

    @Override
    public int ordersSend(Integer order_id) {
        return ordersDao.ordersSend(order_id);
    }

    @Override
    public int overOrders(Integer order_id) {
        return ordersDao.overOrders(order_id);
    }

    @Override
    public int price(Double m,Integer order_id) {
        return 0;
    }

    @Override
    public List<Orders> pendingList() {
        return ordersDao.pendingList();
    }

    @Override
    public List<Orders> endList() {
        return ordersDao.endList();
    }

    @Override
    public List<Orders> overList() {
        return ordersDao.overList();
    }
    @Override
    public List<Orders> findOrdersWithProducts(int userid) {
        return ordersDao.findOrdersWithProducts(userid);
    }

    @Override
    public int deleteOrderByOrderId(int orderId) {
        return ordersDao.deleteOrderByOrderId(orderId);
    }
}
