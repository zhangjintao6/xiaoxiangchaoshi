package com.example.xiaoxiangshop.demos.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.xiaoxiangshop.demos.pojo.Product_types;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zjt
 * @since 2024-09-12
 */
public interface IProduct_typesDao extends BaseMapper<Product_types> {

    //返回所有的父类类型
    List<Product_types> parentTypes();

    //返回所有的根据父类型返回它的子类型
    List<Product_types> childTypes(int parent_id);

    //找到所有类型
    IPage<Product_types> findAllTypes(@Param("page") IPage<Product_types> page);

    //找到所有父类，并将其子类遍历到父类中
    List<Product_types> findAllParentType();
}
