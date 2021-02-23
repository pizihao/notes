package com.example.mqserver.config.listener;

import com.example.mqserver.model.User;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSON;



/**
 * @program: JVMDome
 * @description: 消息消费者
 * @author: liuwenhao
 * @create: 2021-02-23 10:12
 **/
@Component
public class MessageListener {

    /**
     * @param str 接收到的消息
     * @description: TODO
     * @author liuwenaho
     * @date 2021/2/23 12:03
     */
    @RabbitListener(queues = "AOneQueue")
    public void oneQueue(String str) {
        System.out.println("AOneQueue:" + str);
    }

    /**
     * @param str 接收到的消息
     * @description: TODO
     * @author liuwenaho
     * @date 2021/2/23 12:03
     */
    @RabbitListener(queues = "ATwoQueue")
    public void twoQueue(String str) {
        System.out.println("ATwoQueue:" + str);
    }

    /**
     * @param str 接收到的消息
     * @description: TODO
     * @author liuwenaho
     * @date 2021/2/23 12:03
     */
    @RabbitListener(queues = "AThreeQueue")
    public void threeQueue(String str) {
        System.out.println("AThreeQueue:" + str);
    }

    @RabbitListener(queues = "MyRequestTestQueue") // 指定Queue队列
    public void requestTestConsumer(String userJsonString) {
        System.out.println("我是:MyRequestTestQueue队列");
        System.out.println("json字符串为:" + userJsonString);
        // 将json字符串 转换为 User对象
        User user=JSON.parseObject(userJsonString, User.class);
        System.out.println(user.toString());
    }
}