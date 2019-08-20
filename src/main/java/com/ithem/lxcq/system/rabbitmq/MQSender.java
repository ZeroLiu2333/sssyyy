package com.ithem.lxcq.system.rabbitmq;

import com.ithem.lxcq.system.redis.RedisService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MQSender {
    @Autowired
    AmqpTemplate amqpTemplate;
    @Autowired
    RedisService redisService;

    public void send(Object message){
        String msg=redisService.BeanToString(message);
        amqpTemplate.convertAndSend(MQConfig.QUEUE,msg);

    }
    public void sendSeleced(Object message){
        String mm=redisService.BeanToString(message);
        amqpTemplate.convertAndSend(MQConfig.SelectQ,mm);
        System.out.println("send!!");

    }
}
