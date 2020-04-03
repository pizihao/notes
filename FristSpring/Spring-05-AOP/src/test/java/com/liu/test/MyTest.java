package com.liu.test;

import com.liu.service.UserService;
import com.liu.service.UserServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author shidacaizi
 * @date 2020/4/3 12:55
 */
public class MyTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("aplicationContent.xml");
        // 注意动态代理代理的是接口，这里不能使用类
        UserService userService = (UserService) context.getBean("userService");
        userService.add();
    }
}
