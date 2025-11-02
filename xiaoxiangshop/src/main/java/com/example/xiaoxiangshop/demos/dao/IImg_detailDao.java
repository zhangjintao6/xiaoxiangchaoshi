package com.example.xiaoxiangshop.demos.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.xiaoxiangshop.demos.pojo.Img_detail;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zjt
 * @since 2024-09-12
 */
public interface IImg_detailDao extends BaseMapper<Img_detail> {

    List<Img_detail> getImageDetail(int pid);

}
