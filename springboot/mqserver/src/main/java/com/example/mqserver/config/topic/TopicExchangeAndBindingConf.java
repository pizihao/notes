package com.example.mqserver.config.topic;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @program: JVMDome
 * @description: topic配置
 * @author: liuwenhao
 * @create: 2021-02-23 10:15
 **/
@Configuration
public class TopicExchangeAndBindingConf {


    /**
     * 注入Topic路由策略的Exchange交换机实例
     *
     * @return Exchange“交换机”实例
     * @date 2018年7月18日 下午8:47:36
     */
    @Bean(name = "myTopicExchange")
    TopicExchange getTopicExchange() {
        // 创建并返回名为My-Topic-Exchange的交换机
        return new TopicExchange("MyTopicExchange");
    }


    /**
     * 将myFirstQueue对应的Queue绑定到此topicExchange,并指定路由键为"routingKey.#"
     * 即:此Exchange中,路由键以"routingKey."开头的Queue将被匹配到
     *
     * @date 2018年7月19日 上午12:20:09
     */
    @Bean
    Binding bindingQueueOneToTopicExchange(@Qualifier("AOneQueue") Queue myFirstQueue,
                                           @Qualifier("myTopicExchange") TopicExchange myTopicExchange) {
        return BindingBuilder.bind(myFirstQueue).to(myTopicExchange).with("routingKey.#");
    }

    /**
     * 将myTwoQueue对应的Queue绑定到此topicExchange,并指定路由键为"#.topic"
     * 即:此Exchange中,路由键以".topic"结尾的Queue将被匹配到
     *
     * @date 2018年7月19日 上午12:20:09
     */
    @Bean
    Binding bindingQueueTwoToTopicExchange(@Qualifier("ATwoQueue") Queue myTwoQueue,
                                           @Qualifier("myTopicExchange") TopicExchange myTopicExchange) {
        return BindingBuilder.bind(myTwoQueue).to(myTopicExchange).with("#.topic");
    }

    /**
     * 将myThreeQueue对应的Queue绑定到此topicExchange,并指定路由键为"#"
     * 即:此topicExchange中,任何Queue都将被匹配到
     *
     * @date 2018年7月19日 上午12:20:09
     */
    @Bean
    Binding bindingQueueThreeToTopicExchange(@Qualifier("AThreeQueue") Queue myThreeQueue,
                                             @Qualifier("myTopicExchange") TopicExchange myTopicExchange) {
        return BindingBuilder.bind(myThreeQueue).to(myTopicExchange).with("#");
    }
}
