package com.liu.config;

import com.liu.config.pojo.Dog;
import com.liu.config.pojo.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ConfigApplicationTests {

    @Autowired
    private Dog dog;

    @Autowired
    private Person person;

    @Test
    void contextLoads() {
        System.out.println(person);
    }

}
