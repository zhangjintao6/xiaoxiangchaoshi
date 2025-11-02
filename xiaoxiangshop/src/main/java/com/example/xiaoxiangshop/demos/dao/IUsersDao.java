package com.example.xiaoxiangshop.demos.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.xiaoxiangshop.demos.pojo.Users;
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
public interface IUsersDao extends BaseMapper<Users> {
    //查询所有用户，但是管理员除外，不查询
    IPage<Users> findAllUsers(@Param("page") IPage<Users> page);

    void register(Users users);
   List<Users> findUsersByuser_id(int user_id);
}
