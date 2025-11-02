package com.example.xiaoxiangshop.demos.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.xiaoxiangshop.demos.pojo.Carts;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zjt
 * @since 2024-09-12
 */
public interface ICartsDao extends BaseMapper<Carts> {
    //下单
    void addCart(Carts carts);

    //根据用户id查找购物车记录
    List<Carts> findCartsByUserId(int id);

    //跟新商品数量
    void update(Carts carts);

    //根据userid删除购物车信息
    void delCartByUserid(int id);

    //根据product_ids删除购物车信息
    void delCartByPid(int id);

    //返回一个cart并带有product信息根据userid
    List<Carts> findCartByUserid(int id);

}
