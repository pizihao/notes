package com.liu.service;

import com.liu.mapper.UserDao;

/**
 * @author shidacaizi
 * @date 2020/4/1 14:14
 */
public class UserServiceImpl implements  UserService{
    private UserDao userDao;

    @Override
    public void setUserDao(UserDao userDao){
        this.userDao = userDao;
    }

    @Override
    public void listUser() {
        userDao.listUser();
    }
}
