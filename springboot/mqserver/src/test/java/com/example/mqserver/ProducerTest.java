package com.example.mqserver;

import com.example.mqserver.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.alibaba.fastjson.JSON;


/**
 * @program: JVMDome
 * @description: 测试用生产者
 * @author: liuwenhao
 * @create: 2021-02-23 11:55
 **/
@SpringBootTest(classes = MqserverApplication.class)
@RunWith(SpringRunner.class)
public class ProducerTest {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Test
    public void fanoutExchangeTest(){
        amqpTemplate.convertAndSend("FanoutExchange","","OK");
    }

    /**
     * topic路由策略(可以通配的路由键)测试
     *
     * @date 2018年7月18日 下午4:42:54
     */
    @Test
    public void topicExchangeTest1() {
        // 此消息能匹配上 路由键为“routingKey.#”和“#”的队列
        amqpTemplate.convertAndSend("MyTopicExchange", "routingKey.myTest", "1");
    }

    @Test
    public void topicExchangeTest2() {
        // 此消息能匹配上 路由键为“#.topic”和“#”的队列
        amqpTemplate.convertAndSend("MyTopicExchange", "myTest.topic", "2");
    }

    @Test
    public void topicExchangeTest3() {
        // 此消息能匹配上 路由键为“#”的队列
        amqpTemplate.convertAndSend("MyTopicExchange", "myTest", "3");
    }

    /**
     * direct路由策略(具体的路由键)测试
     *
     * @date 2018年7月18日 下午4:42:54
     */
    @Test
    public void directExchangeTest() {
//		rabbitMessagingTemplate.convertAndSend("My-Direct-Exchange", "routingKey.First", "1234578");
        amqpTemplate.convertAndSend("MyDirectExchange", "routingKey.First", "1234578");
    }

    /**
     * direct路由策略---测试HttpClient的请求方法
     *
     * @date 2018年7月19日 下午5:15:01
     */
    @Test
    public void directExchangeRequestTest() {
        User user = new User();
        user.setAge(18);
        user.setGender("女");
        user.setMotto("不感兴趣！");
        user.setName("喵喵");
        String jsonString = JSON.toJSONString(user);
        amqpTemplate.convertAndSend("MyDirectExchange", "requestTest.aa",jsonString);
    }

}