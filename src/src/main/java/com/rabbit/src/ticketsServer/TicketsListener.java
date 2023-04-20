package com.rabbit.src.ticketsServer;

import com.rabbit.src.MSGConvertor;
import com.rabbit.src.Message;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
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
        //List<String> attributes = MSGConvertor.getAttributes(msg);
        Message res;

        if (Objects.equals(message.methodName, "tryBuy")){
            res = new Message(SERVER_NAME, "response", message.proccesID
                    , controller.tryBuyTicket(Integer.valueOf(message.parameters)));
        }
        else {
            res = new Message(SERVER_NAME, "response", message.proccesID, "error:CalledMethodDon'tExist");
        }
        controller.sendMSGToFinance(res.toString());
    }

}
