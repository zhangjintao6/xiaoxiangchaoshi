package com.example.xiaoxiangshop.demos.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.xiaoxiangshop.demos.pojo.Users;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zjt
 * @since 2024-09-12
 */
public interface IUsersService extends IService<Users> {
    IPage<Users> findAllUsers(IPage<Users> page);

    void register(Users users);

    List<Users> findUsersByuser_id(int user_id);
}
