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
    AmqpTemplate template;

    @Autowired
    TicketsController controller;

    @RabbitListener(queues = "finance_tickets")
    void listener(String msg){
        System.out.println("TICKET_SERVICE_SEND_MSG");
        String[] msgs = msg.split("; ");
        template.convertAndSend("financeOrchestr", msgs[0] + "; " + controller.buyTicket(Integer.valueOf(msgs[1])));
    }

}
