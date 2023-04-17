package com.rabbit.src.ticketsServer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@EnableRabbit
@Component
public class TicketsListener {

    @Autowired
    TicketsController controller;

    @RabbitListener(queues = "finance_tickets")
    void listener(String msg){
        String[] msgs = msg.split("; ");
        String res = controller.buyTicket(Integer.valueOf(msgs[1]));
        controller.sendMSGToFinance(msgs[0]+"; "+res);
    }

}
