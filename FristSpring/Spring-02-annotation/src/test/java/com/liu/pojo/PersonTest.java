package com.liu.pojo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

/**
 * @author shidacaizi
 * @date 2020/4/2 11:34
 */
public class PersonTest {

    @Test
    public void testToString() {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        Person person = context.getBean("person", Person.class);

        person.getDog().shout();
        person.getCat().shout();
    }

}
