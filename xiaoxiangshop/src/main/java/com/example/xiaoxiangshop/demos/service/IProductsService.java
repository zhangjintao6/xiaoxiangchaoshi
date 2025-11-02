package com.example.xiaoxiangshop.demos.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.xiaoxiangshop.demos.pojo.Products;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zjt
 * @since 2024-09-12
 */
public interface IProductsService extends IService<Products> {


    List<Products> findProducts(Integer pt_id, Integer ct_id, List<Integer> label_ids,String sort,Integer order,Integer rate);

    List<Products> searchProducts(
            String keyword,List<Integer> label_ids,
            Integer brand_id,Double low,Double high,
            Integer ct_id,Integer rate,
            String sort,Integer order);

    List<Products> findProductsByLabelId(int LabelId);

    List<Products>findProductsByNameOrBrandOrPrice(String keyword);

    List<Products> findProductsByTypeId(int id);

    IPage<Products> findAllProducts(IPage<Products> page);

    IPage<Products> findAllProductsByCheck(IPage<Products> page, QueryWrapper<Products> wrapper);


}