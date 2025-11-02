package com.example.xiaoxiangshop.demos.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.xiaoxiangshop.demos.pojo.Orders;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zjt
 * @since 2024-09-19
 */
public interface IOrdersService extends IService<Orders> {
    List<Orders> findOrdersByUserId(int UserId);
    List<Orders> findOrdersByUserIdandstatus(int UserId,int Status);
    List<Orders>findOrdersWithProducts(int userid);
    int deleteOrderByOrderId(int orderId);


    List<Orders> ordersList();
    //根据id修改订单状态
    int ordersPick(Integer order_id);
    int ordersSend(Integer order_id);
    int overOrders(Integer order_id);
    //修改订单的price字段
    int price(Double m,Integer order_id);
    List<Orders> pendingList();
    List<Orders> endList();
    List<Orders> overList();
}
