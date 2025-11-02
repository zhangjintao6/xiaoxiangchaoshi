package com.example.xiaoxiangshop.demos.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.xiaoxiangshop.demos.pojo.Orders;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zjt
 * @since 2024-09-19
 */
public interface IOrdersDao extends BaseMapper<Orders> {
    List<Orders> findOrdersByUserId(int UserId);

    List<Orders> findOrdersByUserIdandstatus(@Param("UserId") int UserId,@Param("Status") int Status);
    List<Orders>findOrdersWithProducts(int userid);
    int deleteOrderByOrderId(int orderId);

    List<Orders> ordersList();

    //根据id修改订单转态
    int ordersPick(@Param("order_id") Integer order_id);
    int ordersSend(@Param("order_id") Integer order_id);
    int overOrders(@Param("order_id") Integer order_id);
    //修改订单字段
    int price(Double m,Integer order_id);
    List<Orders> pendingList();
    List<Orders> endList();
    List<Orders> overList();

}
