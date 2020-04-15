package com.liu.test;

import com.liu.mapper.UserMySqlImpl;
import com.liu.service.UserService;
import com.liu.service.UserServiceImpl;

/**
 * @author shidacaizi
 * @date 2020/4/1 14:20
 */
public class UserTest {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
//
        userService.setUserDao(new UserMySqlImpl());
        userService.listUser();
    }
}
