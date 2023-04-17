package com.rabbit.src.ticketsServer;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@EnableRabbit
@Component
public class TicketsListener {

    @RabbitListener(queues = "finance_tickets")
    void listener(){

    }

}
