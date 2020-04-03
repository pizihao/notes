package com.wen.test;

import com.wen.pojo.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author shidacaizi
 * @date 2020/4/1 17:18
 */
public class IOCTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");

        User user = (User) context.getBean("newliu");

        user.show();
    }
}
