package com.rabbit.src.financalService;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@EnableRabbit
@Component
public class FinanceListener {

    @RabbitListener(queues = "financeOrchestr")
    void listenerOrchester(String msg){
        System.out.println(msg);
    }
}
