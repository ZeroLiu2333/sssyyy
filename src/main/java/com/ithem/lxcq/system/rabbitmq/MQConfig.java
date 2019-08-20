package com.ithem.lxcq.system.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class MQConfig {
    public static final String QUEUE="queue";
    public static final String SelectQ="SelectQueue";
    @Bean
    public Queue queue() {
        return new Queue(QUEUE,true);
    }
    @Bean
    public Queue Selectqueue(){
        return new Queue(SelectQ,true);
    }
}
