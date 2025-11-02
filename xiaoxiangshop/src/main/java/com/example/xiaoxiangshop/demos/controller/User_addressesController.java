package com.example.xiaoxiangshop.demos.controller;


import com.example.xiaoxiangshop.demos.pojo.User_addresses;
import com.example.xiaoxiangshop.demos.service.IUser_addressesService;
import com.example.xiaoxiangshop.demos.vo.ResponseMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zjt
 * @since 2024-09-12
 */
@RestController
@RequestMapping("/user_addresses")
public class User_addressesController {

    @Autowired
    IUser_addressesService addressesService;

    //新增地址
    @RequestMapping("addressAdd")
    ResponseMsg addressAdd(@RequestBody User_addresses addressData) {
        System.out.println(addressData.toString());
        addressesService.addressAdd(addressData);
        return ResponseMsg.success("成功");
    }

    //将其他的默认地址清零
    @RequestMapping("setAddressDefault")
    ResponseMsg setAddressDefault() {
        addressesService.setDefaultAddress();
        return ResponseMsg.success("设置成功");
    }

    @RequestMapping("list")
    List<User_addresses> list() {
        return addressesService.list();
    }

    //根据id删除数据
    @RequestMapping("del")
    ResponseMsg del(@RequestParam("id") Integer id) {
        addressesService.removeById(id);
        return ResponseMsg.success("删除成功");
    }

    //根据id查找地址
    @RequestMapping("findAddressById")
    User_addresses findAddressById(Integer id) {
        return addressesService.getById(id);
    }

}

