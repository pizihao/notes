package com.liu.alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author shidacaizi
 * @date 2020/5/4 10:49
 */
@SpringBootApplication
@EnableDiscoveryClient
public class AlibabaApplication83 {
    public static void main(String[] args) {
        SpringApplication.run(AlibabaApplication83.class, args);
    }
}