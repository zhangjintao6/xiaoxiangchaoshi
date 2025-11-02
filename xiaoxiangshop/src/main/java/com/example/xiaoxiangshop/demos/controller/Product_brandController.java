package com.example.xiaoxiangshop.demos.controller;


import com.example.xiaoxiangshop.demos.pojo.Product_brand;
import com.example.xiaoxiangshop.demos.service.impl.Product_brandServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zjt
 * @since 2024-09-18
 */
@RestController
@RequestMapping("/product_brand")
public class Product_brandController {

    @Autowired
    Product_brandServiceImpl brandService;

    @RequestMapping("getAllBrand")
    List<Product_brand> getAllBrand(){
        return brandService.list();
    }
}

