package com.example.xiaoxiangshop.demos.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.xiaoxiangshop.demos.dao.IUsersDao;
import com.example.xiaoxiangshop.demos.pojo.Users;
import com.example.xiaoxiangshop.demos.service.IUsersService;
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
public class UsersServiceImpl extends ServiceImpl<IUsersDao, Users> implements IUsersService {

    @Autowired
    IUsersDao usersDao;

    @Override
    public IPage<Users> findAllUsers(IPage<Users> page) {
        return usersDao.findAllUsers(page);
    }

    @Override
    public void register(Users users) {
        usersDao.register(users);
    }

    @Override
    public List<Users> findUsersByuser_id(int user_id) {
        return usersDao.findUsersByuser_id(user_id);
    }


}
