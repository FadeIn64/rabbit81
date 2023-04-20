package com.rabbit.src.financalService;

import com.rabbit.src.Message;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@EnableRabbit
@Component
public class FinanceListener {

    @Autowired
    FinanceController controller;

    @Autowired
    FinanceOrchestr orchestr;

    @RabbitListener(queues = "financeOrchestr")
    void listenerOrchester(String msg){
        Message message = new Message(msg);
        String responseMethodName = message.methodName.split(":")[1];

        if (responseMethodName.equals("error")){
            System.out.println(message);
            return;
        }

        if (Objects.equals(message.serverName, "tickets")){
            if ("tryBuy".equals(responseMethodName)){
                orchestr.afterTryBuyTicket(Integer.parseInt(message.proccesID), message.parameters);
            }
            else if ("buy".equals(responseMethodName)){
                orchestr.afterBuyTicket(Integer.parseInt(message.proccesID), message.parameters);
            }
            return;
        }
        if (Objects.equals(message.serverName, "ucs")) {
            orchestr.afterVerifyUser(Integer.parseInt(message.proccesID), message.parameters);
        }

    }

    @RabbitListener(queues = "orchestFinance")
    void listenerController(String msg){
        System.out.println("!!!!!!!!!!!");
        System.out.println(msg);
        controller.addOrder(msg);
    }
}
