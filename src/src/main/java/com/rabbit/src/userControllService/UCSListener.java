package com.rabbit.src.userControllService;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@EnableRabbit
@Component
public class UCSListener {

    @RabbitListener(queues = "finance_UCS")
    void listener(){

    }


}
