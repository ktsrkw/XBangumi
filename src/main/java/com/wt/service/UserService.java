package com.wt.service;

import com.wt.pojo.User;

public interface UserService {
    //根据用户id得到用户
    User getUserById(Integer id);

    //根据用户名得到用户
    User getUserByUsername(String username);

    //注册新用户
    int registerAnUser(String username,String password);

    //更新用户信息
    int updateUserInfo(User user);

    //根据用户id删除用户
    int deleteUserByUserId(Integer userId);
}
