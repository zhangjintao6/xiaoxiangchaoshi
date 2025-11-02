package com.example.xiaoxiangshop.demos.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.example.xiaoxiangshop.demos.pojo.Products;
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
public interface IProductsDao extends BaseMapper<Products> {

    List<Products> findProducts
            (@Param("pt_id") Integer pt_id, @Param("ct_id") Integer ct_id
                    , @Param("label_ids") List<Integer> label_ids,
             @Param("sort") String sort, @Param("order") Integer order,@Param("rate") Integer rate);


    List<Products> searchProducts(
            @Param("keyword") String keyword,@Param("label_ids") List<Integer> label_ids,
            @Param("brand_id") Integer brand_id,@Param("low") Double low,@Param("high") Double high,
            @Param("ct_id") Integer ct_id,@Param("rate") Integer rate,
            @Param("sort") String sort, @Param("order") Integer order);


    List<Products> findProductsByLabelId(int LabelId);

    List<Products>findProductsByNameOrBrandOrPrice(String keyword);



    //查询所有
    IPage<Products> findAllProducts(@Param("page") IPage<Products> page);
    //根据名字模糊查询
    IPage<Products> findAllProductsByCheck(@Param("page") IPage<Products> page, @Param(Constants.WRAPPER) QueryWrapper<Products> wrapper);

//    根据typeId找到商品
    List<Products> findProductsByTypeId(int id);


}
