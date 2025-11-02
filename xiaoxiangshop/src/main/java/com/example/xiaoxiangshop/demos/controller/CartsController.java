package com.example.xiaoxiangshop.demos.controller;


import com.example.xiaoxiangshop.demos.pojo.Carts;
import com.example.xiaoxiangshop.demos.pojo.Order_details;
import com.example.xiaoxiangshop.demos.pojo.Orders;
import com.example.xiaoxiangshop.demos.service.ICartsService;
import com.example.xiaoxiangshop.demos.service.IOrder_detailsService;
import com.example.xiaoxiangshop.demos.service.IOrdersService;
import com.example.xiaoxiangshop.demos.vo.ResponseMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zjt
 * @since 2024-09-12
 */
@RestController
@RequestMapping("/carts")
public class CartsController {
    @Autowired
    ICartsService cartsService;
    @Autowired
    IOrdersService ordersService;
    @Autowired
    IOrder_detailsService detailsService;

    @RequestMapping("addCart")
    ResponseMsg addCart(@RequestBody Carts carts) {
        //根据user_id 找到该用户的所有商品记录
        List<Carts> list = cartsService.findCartsByUserId(carts.getUser_id());
        boolean isExit = true;
        int num = 0;
        //遍历商品集合判断是否有相同商品
        for (Carts c : list
        ) {
            //有相同商品数量相加
            if (Objects.equals(carts.getProduct_id(), c.getProduct_id())) {
                isExit = false;
                num = c.getQuantity() + carts.getQuantity();
                break;
            }
        }

        if (isExit) {
            cartsService.addCart(carts);
        } else {
            carts.setQuantity(num);
            cartsService.update(carts);
        }
        return ResponseMsg.success("成功");
    }

    //根据用户id返回用户购物车信息
    @RequestMapping("cartProductsByUserid")
    List<Carts> cartProductsByUserid(int userid) {
        return cartsService.findCartByUserid(userid);
    }

    //清空购物车数据
    @RequestMapping("delCart")
    ResponseMsg delCart(int userid) {
        cartsService.delCartByUserid(userid);
        return ResponseMsg.success("成功");
    }

    //删除单个购物车信息
    @RequestMapping("del")
    ResponseMsg del(int pid) {
        cartsService.removeById(pid);
        return ResponseMsg.success("成功");
    }

    @RequestMapping("removeById")
    ResponseMsg removeById(@RequestBody List<Integer> id) {
        System.out.println("13413241341234134134"+id);
        cartsService.removeByIds(id);
        return ResponseMsg.success("成功");
    }

    @RequestMapping("submit")
    ResponseMsg submit(@RequestBody Orders orders) {
        System.out.println(orders);
        ordersService.save(orders);
        return ResponseMsg.success("提交成功");
    }

    @RequestMapping("detail")
    ResponseMsg detail(@RequestBody List<Order_details> orderDetails) {
        detailsService.saveBatch(orderDetails);
        return ResponseMsg.success("成功");
    }
}

