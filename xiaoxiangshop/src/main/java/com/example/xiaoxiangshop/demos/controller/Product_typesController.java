package com.example.xiaoxiangshop.demos.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.xiaoxiangshop.demos.pojo.Label;
import com.example.xiaoxiangshop.demos.pojo.Product_types;
import com.example.xiaoxiangshop.demos.pojo.Products;
import com.example.xiaoxiangshop.demos.service.IProduct_typesService;
import com.example.xiaoxiangshop.demos.service.IProductsService;
import com.example.xiaoxiangshop.demos.util.uploadImg;
import com.example.xiaoxiangshop.demos.vo.ResponseMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
@RequestMapping("/product_types")
public class Product_typesController {
    @Autowired
    IProduct_typesService typesService;
    @Autowired
    IProductsService productsService;

    //得到所有父级的类型
    @RequestMapping("getParentType")
    List<Product_types> getParentType() {
        return typesService.parentTypes();
    }

    //得到所有的子类型
    @RequestMapping("getChildType")
    List<Product_types> getChildType(@RequestParam("parent_id") int parent_id) {
        return typesService.childTypes(parent_id);
    }

    //根据类型找到对应的商品并返回一个type类型的数据
    @RequestMapping("getProductBytypeId")
    List<Product_types> getProductByTypeId(int id){
        List<Products> products = productsService.findProductsByTypeId(id);
        List<Product_types> productTypes = typesService.list();
        for (Product_types p : productTypes
        ) {
            p.setProducts(products);
        }
        return productTypes;
    }

    @RequestMapping("findAllTypes")
    IPage<Product_types> findAllTypes(@RequestParam int page,
                                      @RequestParam int limit,
                                      @RequestParam String check){
        if (check != null && !check.isEmpty()){
            QueryWrapper<Product_types> wrapper = new QueryWrapper<>();
            wrapper.like("type_name",check);
            return typesService.page(new Page<>(page,limit),wrapper);
        }
        return typesService.findAllTypes(new Page<>(page,limit));
    }

    @RequestMapping("getAllType")
    List<Product_types> getAllType(){
        return typesService.findAllParentType();
    }

    @RequestMapping("upload")
    ResponseMsg upload(MultipartFile file) {
        String path = "E:/JAVA_PRO/vivo/gitee/xiaoxiangshop/vue-admin/public/typeImg/";
        return uploadImg.uploadImgs(file, path);
    }

    @RequestMapping("doUpdate")
    Boolean doUpdate(@RequestBody Product_types type){
        UpdateWrapper<Product_types> wrapper = new UpdateWrapper<>();
        wrapper.eq("type_id",type.getType_id());
        if (type.getType_name() != null && !type.getType_name().isEmpty()){
            wrapper.set("type_name",type.getType_name());
        }
        if (type.getIs_parent() == 0){
            wrapper.set("is_parent",0);
            wrapper.set("parent_id",type.getParent_id());
        }else{
            wrapper.set("is_parent",1);
            wrapper.set("parent_id",null);
        }
        if (type.getImg() != null && !type.getImg().isEmpty()){
            wrapper.set("img",type.getImg());
        }
        return typesService.update(wrapper);
    }

    @RequestMapping("doAdd")
    Boolean doAdd(@RequestBody Product_types type){
        if (type.getIs_parent() == 0){
            type.setIs_parent(0);
            type.setParent_id(type.getParent_id());
        }else{
            type.setIs_parent(1);
            type.setParent_id(null);
        }
        return typesService.save(type);
    }

    @RequestMapping("delByIds")
    Boolean delByIds(@RequestBody List<Integer> ids){
        return typesService.removeByIds(ids);
    }
}

