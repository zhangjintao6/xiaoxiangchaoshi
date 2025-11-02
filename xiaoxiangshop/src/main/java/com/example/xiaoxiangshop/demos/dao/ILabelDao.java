package com.example.xiaoxiangshop.demos.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.xiaoxiangshop.demos.pojo.Label;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zjt
 * @since 2024-09-12
 */
public interface  ILabelDao extends BaseMapper<Label> {

    List<Label> findLabelByPid();
}
