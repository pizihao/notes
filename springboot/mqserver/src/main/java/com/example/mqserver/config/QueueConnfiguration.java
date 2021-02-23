package com.example.mqserver.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @program: JVMDome
 * @description: Queue配置注入
 * @author: liuwenhao
 * @create: 2021-02-23 09:58
 **/
@Configuration
public class QueueConnfiguration {

    @Bean(name = "AOneQueue")
    public Queue getFirstQueue() {
        return new Queue("AOneQueue");
    }

    @Bean(name = "ATwoQueue")
    public Queue getTwoQueue() {
        return new Queue("ATwoQueue");
    }

    @Bean(name = "AThreeQueue")
    public Queue getThreeQueue() {
        return new Queue("AThreeQueue");
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(new Jackson2JsonMessageConverter());
        return template;
    }

    @Bean(name = "MyRequestTestQueue")
    public Queue getRequestTestQueue() {
        return new Queue("MyRequestTestQueue");
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        return factory;
    }
}