package helloworld.liu.test;

import helloworld.liu.mapper.UserMySqlImpl;
import helloworld.liu.service.UserService;
import helloworld.liu.service.UserServiceImpl;

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
