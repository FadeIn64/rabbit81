package com.rabbit.src.ticketsServer;

import com.rabbit.src.Message;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@EnableRabbit
@Component
public class TicketsListener {

    private static final String SERVER_NAME = "tickets";

    @Autowired
    TicketsController controller;

    @RabbitListener(queues = "finance_tickets")
    void listener(String msg){
        Message message = new Message(msg);
        Message res;

        if (Objects.equals(message.methodName, "tryBuy")){
            res = new Message(SERVER_NAME, "response:tryBuy", message.proccesID
                    , controller.tryBuyTicket(Integer.valueOf(message.parameters)));
        }
        else if (Objects.equals(message.methodName, "buy")){
            res = new Message(SERVER_NAME, "response:buy", message.proccesID
                    , controller.buyTicket(Integer.valueOf(message.parameters)));
        }
        else {
            res = new Message(SERVER_NAME, "response:error", message.proccesID, "error:CalledMethodDon'tExist!!!" + message);
        }
        controller.sendMSGToFinance(res.toString());
    }

}
