package com.example.xiaoxiangshop.demos.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.xiaoxiangshop.demos.dao.IProduct_typesDao;
import com.example.xiaoxiangshop.demos.pojo.Product_types;
import com.example.xiaoxiangshop.demos.service.IProduct_typesService;
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
public class Product_typesServiceImpl extends ServiceImpl<IProduct_typesDao, Product_types> implements IProduct_typesService {

    @Autowired
    IProduct_typesDao typesDao;

    @Override
    public List<Product_types> parentTypes() {
        return typesDao.parentTypes();
    }

    @Override
    public List<Product_types> childTypes(int parent_id) {
        return typesDao.childTypes(parent_id);
    }

    @Override
    public IPage<Product_types> findAllTypes(IPage<Product_types> page) {
        return typesDao.findAllTypes(page);
    }

    @Override
    public List<Product_types> findAllParentType() {
        return typesDao.findAllParentType();
    }
}
