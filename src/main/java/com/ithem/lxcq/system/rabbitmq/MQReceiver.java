package com.ithem.lxcq.system.rabbitmq;

import com.ithem.lxcq.system.redis.RedisService;
import com.ithem.lxcq.system.service.SelectedService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ithem.lxcq.system.domain.user;

@Service
public class MQReceiver {
    @Autowired
    RedisService redisService;
    @Autowired
    SelectedService selectedService;

    @RabbitListener(queues = MQConfig.QUEUE)
    public void receive(String message){
        System.out.println(message);
    }
    @RabbitListener(queues = MQConfig.SelectQ)
    public void receiveSelected(String message){
        SelectMQ mm=redisService.stringToBean(message,SelectMQ.class);
        user user=mm.getUser();
        int cid=mm.getCid();
        selectedService.select(user,cid);
        System.out.println("redceive!!");
    }
}
