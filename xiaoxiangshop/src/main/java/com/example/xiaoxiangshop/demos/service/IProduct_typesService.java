package com.example.xiaoxiangshop.demos.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.xiaoxiangshop.demos.pojo.Product_types;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zjt
 * @since 2024-09-12
 */
public interface IProduct_typesService extends IService<Product_types> {

    List<Product_types> parentTypes();

    List<Product_types> childTypes(int parent_id);

    IPage<Product_types> findAllTypes(IPage<Product_types> page);

    //找到所有父类，并将其子类遍历到父类中
    List<Product_types> findAllParentType();
}
