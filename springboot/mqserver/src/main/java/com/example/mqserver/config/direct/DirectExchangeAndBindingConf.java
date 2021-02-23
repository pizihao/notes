package com.example.mqserver.config.direct;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: JVMDome
 * @description: Direct配置
 * @author: liuwenhao
 * @create: 2021-02-23 10:14
 **/

@Configuration
public class DirectExchangeAndBindingConf {
    /**
     * 注入Direct路由策略的Exchange交换机实例
     *
     * @return Exchange“交换机”实例
     * @date 2018年7月18日 下午8:47:48
     */
    @Bean(name = "myDirectExchange")
    DirectExchange getDirectExchange() {
        // 创建并返回名为My-Direct-Exchange的交换机
        return new DirectExchange("My-Direct-Exchange");
    }

    /**
     * 将Queue绑定到此directExchange,并指定路由键为"routingKey.First"
     *
     * @date 2018年7月19日 上午12:20:09
     */
    @Bean
    Binding bindingQueueOneToDirectExchange(@Qualifier("AOneQueue") Queue myFirstQueue,
                                            @Qualifier("myDirectExchange") DirectExchange myDirectExchange) {
        return BindingBuilder.bind(myFirstQueue).to(myDirectExchange).with("routingKey.First");
    }

    /**
     * 将Queue绑定到此directExchange,并指定路由键为"requestTest"
     *
     * @date 2018年7月19日 上午12:20:09
     */
    @Bean
    Binding bindingQueueRequestTestToDirectExchange(@Qualifier("MyRequestTestQueue") Queue myRequestTestQueue,
                                                    @Qualifier("myDirectExchange") DirectExchange myDirectExchange) {
        return BindingBuilder.bind(myRequestTestQueue).to(myDirectExchange).with("requestTest.aa");
    }
}