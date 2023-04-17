package com.rabbit.src;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@EnableRabbit
@Component
public class SampleListener {

    @RabbitListener(queues = "queue1")
    void listener(String msg){
        System.out.println(msg);
    }

}
