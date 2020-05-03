package com.liu.consumer.controller;

import com.liu.api.pojo.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author shidacaizi
 * @date 2020/5/1 19:38
 */
@Controller
@ResponseBody
public class DeptConsumerController {

    // 消费者不应该有service层
    // Spring boot 支持Restful请求
    // RestTemplate 。。。。供我们直接调用就可以了，
    // 注册到Spring中

    @Autowired
    private RestTemplate restTemplate;

    //通过ribbon访问的应该是变量
    //private static final String REST_URL_PREFIX = "http://localhost:8001";
    private static final String REST_URL_PREFIX = "http://PROVIDER-DEPT";

    @RequestMapping("/consumer/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id){
        return restTemplate.getForObject(REST_URL_PREFIX + "/dept/get/"+id, Dept.class);
    }

    @RequestMapping("/consumer/dept/add")
    public boolean add(Dept dept){
        return restTemplate.postForObject(REST_URL_PREFIX + "/dept/add", dept, Boolean.class);
    }

    @RequestMapping("/consumer/dept/getList")
    public List<Dept> getList(){
        return restTemplate.getForObject(REST_URL_PREFIX + "/dept/getList", List.class);
    }



}
