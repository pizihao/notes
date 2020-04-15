package com.liu.service;

import com.liu.mapper.UserDao;

/**
 * @author shidacaizi
 * @date 2020/4/1 14:07
 */
public interface UserService {
    void listUser();

    void setUserDao(UserDao userDao);
}
