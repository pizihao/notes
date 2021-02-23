package com.example.mqserver.config.fanout;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: JVMDome
 * @description: fanout交换机配置
 * @author: liuwenhao
 * @create: 2021-02-23 10:10
 **/
@Configuration
public class FanoutExchangeAndBindingConf {

    /**
     * @description: Fanout策略的交换机
     * @return org.springframework.amqp.core.FanoutExchange
     * @author liuwenaho
     * @date 2021/2/23 11:42
     */
    @Bean(name = "FanoutExchange")
    FanoutExchange getFanoutExchange(){
        return new FanoutExchange("FanoutExchange");
    }

    /**
     * @description: 把队列和交换机进行绑定
     * @param AOneQueue 消息队列实体
     * @param fanoutExchange fanout策略的交换机
     * @return org.springframework.amqp.core.Binding
     * @author liuwenaho
     * @date 2021/2/23 11:51
     */
    @Bean
    Binding bindingQueueOneToFanoutExchange(@Qualifier("AOneQueue") Queue AOneQueue,
                                            @Qualifier("FanoutExchange") FanoutExchange fanoutExchange){
        return BindingBuilder.bind(AOneQueue).to(fanoutExchange);
    }

    /**
     * @description: 把队列和交换机进行绑定
     * @param ATwoQueue 消息队列实体
     * @param fanoutExchange fanout策略的交换机
     * @return org.springframework.amqp.core.Binding
     * @author liuwenaho
     * @date 2021/2/23 11:51
     */
    @Bean
    Binding bindingQueueTwoToFanoutExchange(@Qualifier("ATwoQueue") Queue ATwoQueue,
                                            @Qualifier("FanoutExchange") FanoutExchange fanoutExchange){
        return BindingBuilder.bind(ATwoQueue).to(fanoutExchange);
    }

    /**
     * @description: 把队列和交换机进行绑定
     * @param AThreeQueue 消息队列实体
     * @param fanoutExchange fanout策略的交换机
     * @return org.springframework.amqp.core.Binding
     * @author liuwenaho
     * @date 2021/2/23 11:51
     */
    @Bean
    Binding bindingQueueThreeToFanoutExchange(@Qualifier("AThreeQueue") Queue AThreeQueue,
                                            @Qualifier("FanoutExchange") FanoutExchange fanoutExchange){
        return BindingBuilder.bind(AThreeQueue).to(fanoutExchange);
    }
}