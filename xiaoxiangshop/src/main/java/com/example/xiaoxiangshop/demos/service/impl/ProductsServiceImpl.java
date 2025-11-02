package com.example.xiaoxiangshop.demos.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.xiaoxiangshop.demos.dao.IProductsDao;
import com.example.xiaoxiangshop.demos.pojo.Products;
import com.example.xiaoxiangshop.demos.service.IProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zjt
 * @since 2024-09-12
 */
@Service
public class ProductsServiceImpl extends ServiceImpl<IProductsDao, Products> implements IProductsService {

    @Autowired
    IProductsDao productsDao;


    @Override
    public List<Products> findProducts(Integer pt_id, Integer ct_id, List<Integer> label_ids, String sort, Integer order, Integer rate) {
        return productsDao.findProducts(pt_id,ct_id,label_ids,sort,order,rate);
    }

    @Override
    public List<Products> searchProducts(String keyword, List<Integer> label_ids, Integer brand_id, Double low, Double high, Integer ct_id, Integer rate, String sort, Integer order) {
        return productsDao.searchProducts(keyword,label_ids,brand_id,low,high,ct_id,rate,sort,order);
    }


    @Override
    public List<Products> findProductsByLabelId(int LabelId) {
        return productsDao.findProductsByLabelId(LabelId);
    }

    @Override
    public List<Products> findProductsByNameOrBrandOrPrice(String keyword) {
        return productsDao.findProductsByNameOrBrandOrPrice(keyword);
    }

    @Override
    public List<Products> findProductsByTypeId(int id) {
        return productsDao.findProductsByTypeId(id);
    }

    @Override
    public IPage<Products> findAllProducts(IPage<Products> page) {
        return productsDao.findAllProducts(page);
    }

    @Override
    public IPage<Products> findAllProductsByCheck(IPage<Products> page, QueryWrapper<Products> wrapper) {
        return productsDao.findAllProductsByCheck(page,wrapper);
    }


}

