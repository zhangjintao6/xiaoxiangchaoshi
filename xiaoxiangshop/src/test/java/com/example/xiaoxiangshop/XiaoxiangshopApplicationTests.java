package com.example.xiaoxiangshop;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.xiaoxiangshop.demos.dao.IProduct_typesDao;
import com.example.xiaoxiangshop.demos.dao.IProductsDao;
import com.example.xiaoxiangshop.demos.dao.IUsersDao;
import com.example.xiaoxiangshop.demos.pojo.Product_types;
import com.example.xiaoxiangshop.demos.pojo.Products;
import com.example.xiaoxiangshop.demos.pojo.Users;
import com.example.xiaoxiangshop.demos.service.IProduct_typesService;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class XiaoxiangshopApplicationTests {

    @Autowired
    IProductsDao productsDao;
    @Autowired
    IUsersDao usersDao;
    @Autowired
    IProduct_typesDao typesDao;

    @Test
    void contextLoads() {
//        IPage<Products> list = productsDao.findAllProducts(new Page<>(1,5));
//        for (Products p:list.getRecords()){
//            System.out.println(p.toString());
//        }
//        IPage<Users> list = usersDao.findAllUsers(new Page<>(1,5));
//        for (Users u:list.getRecords()){
//            System.out.println(u.toString());
//        }
//        List<Product_types> list = typesService.parentTypes();
//        for (Product_types type:list){
//            System.out.println(type.toString());
//        }
//        IPage<Product_types> list = typesDao.findAllTypes(new Page<>(1,5));
//        for (Product_types type:list.getRecords()){
//            System.out.println(type.toString());
//        }
        List<Product_types> list = typesDao.findAllParentType();
        for (Product_types type: list){
            System.out.println(type.getChildType().toString());
        }
    }

}
