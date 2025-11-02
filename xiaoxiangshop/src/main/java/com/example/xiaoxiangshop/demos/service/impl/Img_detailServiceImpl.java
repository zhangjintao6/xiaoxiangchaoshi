package com.example.xiaoxiangshop.demos.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.xiaoxiangshop.demos.dao.IImg_detailDao;
import com.example.xiaoxiangshop.demos.pojo.Img_detail;
import com.example.xiaoxiangshop.demos.service.IImg_detailService;
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
public class Img_detailServiceImpl extends ServiceImpl<IImg_detailDao, Img_detail> implements IImg_detailService {

    @Autowired
    IImg_detailDao imgDetailDao;

    @Override
    public List<Img_detail> getImageDetail(int pid) {
        return imgDetailDao.getImageDetail(pid);
    }
}
