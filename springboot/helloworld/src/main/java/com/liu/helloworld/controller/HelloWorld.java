package com.liu.helloworld.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shidacaizi
 * @date 2020/4/16 23:47
 */
@RestController
public class HelloWorld {

    @RequestMapping("/hello")
    public String hello() {
        return "hello,world";
    }
}
