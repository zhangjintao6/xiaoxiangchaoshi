package com.example.xiaoxiangshop.demos.controller;


import com.example.xiaoxiangshop.demos.pojo.Orders;
import com.example.xiaoxiangshop.demos.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zjt
 * @since 2024-09-19
 */
@RestController
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    IOrdersService ordersService;
    @RequestMapping("/listByUserId")/*根据传过来的标签用户id查出来所有该用户订单*/
    List<Orders> findOrdersByUserId(int UserId) {
        return ordersService.findOrdersByUserId(UserId);
    }

    @RequestMapping("/listByUserIdandstatus")/*根据传过来的标签用户id查出来所有该用户订单*/
    List<Orders> findOrdersByUserIdandstatus(int UserId,Integer Status) {
        return ordersService.findOrdersByUserIdandstatus(UserId,Status);
    }


    @RequestMapping("/findOrdersWithProducts")/*根据传过来的标签用户id查出来所有该用户订单*/
    List<Orders> findOrdersWithProducts(int userid) {
        return ordersService.findOrdersWithProducts(userid);
    }
    @RequestMapping("/deleteOrderByOrderId")/*根据传过来的标签用户id查出来所有该用户订单*/
   int deleteOrderByOrderId(Integer orderId) {
//       return ordersService.deleteOrderByOrderId(orderId);
       return ordersService.removeById(orderId)?1:0;
   }




    //登录

    //遍历订单状态为4（备货完成的订单）到抢单大厅
    @GetMapping("ordersList")
    List<Orders> ordersList(){
        List<Orders> list=ordersService.ordersList();
        //遍历订单的价格总额，不足一百骑手的一单的配送费是4元
        //总额超过一百，骑手每单的配送费为订单总额x0.04
        for (Orders orders:list) {
            if(orders.getTotal_amount()<100){
                Double m=4.0;
                orders.setPrice(4.0);
                ordersService.price(m, Math.toIntExact(orders.getOrder_id()));
            }else {
                orders.setPrice(orders.getTotal_amount()*0.04);
            }
        }
        return list;
    }
    //通过前端传的订单id（已经被抢的订单）修改状态为5（待取货）状态

    @GetMapping("ordersPickup")
    int ordersPickup(Integer order_id){
        System.out.println(order_id);
        ordersService.ordersPick(order_id);
        return 1;
    }
    //遍历订单状态为5（待取货的订单）到待取货
    @RequestMapping("pendingList")
    List<Orders> pendingList(){
        List<Orders> list=ordersService.pendingList();
        for (Orders orders:list) {
            if(orders.getTotal_amount()<100){
                orders.setPrice(4.0);
            }else {
                orders.setPrice(orders.getTotal_amount()*0.04);
            }
        }
        return list;
    }
    //接受前端传过来的orders对象的集合并修改状态为6
    @RequestMapping("acceptOrders")
    int acceptOrders(@RequestBody ArrayList<Orders> orders){
        for (Orders o:orders) {
            ordersService.ordersSend(Math.toIntExact(o.getOrder_id()));
        }
        return 1;
    }
    //遍历订单状态为6（配送中的订单）到待取货
    @RequestMapping("endList")
    List<Orders> endList(){
        List<Orders> list=ordersService.endList();
        for (Orders orders:list) {
            if(orders.getTotal_amount()<100){
                orders.setPrice(4.0);
            }else {
                orders.setPrice(orders.getTotal_amount()*0.04);
            }
        }
        return list;
    }
    //根据id修改状态为7（待评价）
    @GetMapping("overOrders")
    int overOrders(Integer order_id){
        ordersService.overOrders(order_id);
        return 1;
    }
    //遍历最后配送完成的订单
    @GetMapping("overList")
    List<Orders> overList(){
        List<Orders> list=ordersService.overList();

        for (Orders orders:list) {
            if(orders.getTotal_amount()<100){
                orders.setPrice(4.0);
            }else {
                orders.setPrice(orders.getTotal_amount()*0.04);
            }
        }
        return list;
    }
}

