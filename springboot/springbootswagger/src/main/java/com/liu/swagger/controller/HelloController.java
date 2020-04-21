package com.liu.swagger.controller;

import io.swagger.annotations.SwaggerDefinition;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shidacaizi
 * @date 2020/4/20 16:23
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
}
