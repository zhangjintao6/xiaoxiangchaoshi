package com.example.xiaoxiangshop.demos.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.xiaoxiangshop.demos.pojo.Img_detail;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zjt
 * @since 2024-09-12
 */
public interface IImg_detailService extends IService<Img_detail> {

    List<Img_detail> getImageDetail(int pid);
}
