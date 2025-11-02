package com.example.xiaoxiangshop.demos.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.xiaoxiangshop.demos.pojo.Users;
import com.example.xiaoxiangshop.demos.service.impl.UsersServiceImpl;
import com.example.xiaoxiangshop.demos.util.JwtUtils;
import com.example.xiaoxiangshop.demos.util.RedisUtils;
import com.example.xiaoxiangshop.demos.vo.ResponseMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zjt
 * @since 2024-09-12
 */
@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    UsersServiceImpl usersService;
    @Autowired
    RedisUtils redisUtils;

    @RequestMapping("login")
    ResponseMsg login(@RequestBody Users user) {
        QueryWrapper<Users> wrapper = new QueryWrapper<>();
        wrapper.eq("username", user.getUsername());
        wrapper.eq("password", user.getPassword());
        Users u = usersService.getOne(wrapper);
        if (u == null) {
            return ResponseMsg.loginFail();
        }
        //jwt token(类似于令牌)
        String uuid = UUID.randomUUID().toString();
        String subject = "{'user_id':'" + u.getUser_id() +   //用户id
                "','nickname':'" + u.getNickname() +         //昵称
                "','birthday':'" + u.getBirthday() +         //生日
                "','img':'" + u.getImg() +                   //头像
                "','role_id':" + u.getRole_id() + "}";      //登录账户的身份
        String jwtToken = JwtUtils.createJWT(uuid, subject, 1000 * 60 * 60 * 4);
        //redisUtils.set(jwtToken,u,1000*60*60*4);//string类型
        return ResponseMsg.loginSuccess(jwtToken);
    }

    @RequestMapping("findAllUser")
    IPage<Users> findAllUser(@RequestParam int page,
                             @RequestParam int limit) {
        return usersService.findAllUsers(new Page<>(page, limit));
    }

    @RequestMapping("register")
    ResponseMsg register(@RequestBody Users user) {
        System.out.println(user);
        usersService.register(user);
        return ResponseMsg.success("成功");
    }
    @RequestMapping("/listByUserId")/*根据传过来的标签id查出来标签商品*/
    List<Users> listByUserId(int user_id) {
        return usersService.findUsersByuser_id(user_id);
    }
}

