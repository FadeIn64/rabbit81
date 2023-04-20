package com.rabbit.src.financalService;

import com.rabbit.src.Message;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class FinanceOrchestr {

    private static final String SERVER_NAME = "FinanceOrchestr";
    private static int proccesID = 0;

    @Autowired
    AmqpTemplate template;

    Map<Integer, Order> inWorkOrders = new HashMap<>();

    public void createOrder(String userToken, int ticketID){
        Order order = new Order(userToken, ticketID, "none");
        inWorkOrders.put(proccesID, order);

        sendMessageToUCS((new Message(SERVER_NAME, "verify", String.valueOf(proccesID), userToken)).toString());
    }

    void afterVerifyUser(Integer proccesID, String result){
        Order order = inWorkOrders.get(proccesID);
        if ("false".equals(result)){
            order.status = "Order doesn't exist : user not verify";

            inWorkOrders.remove(proccesID);
            sendMessageToFinanceController(order.toString());
            return;
        }
        sendMessageToTickets((new Message(SERVER_NAME, "tryBuy", String.valueOf(proccesID), String.valueOf(order.ticketId))).toString());
    }

    void afterTryBuyTicket(Integer proccesID, String response){

        String status = response.split(":")[0];
        String description = response.split(":")[1];

        Order order = inWorkOrders.get(proccesID);

        if ("error".equals(status)){
            order.status = "Order doesn't exist : " + description;

            inWorkOrders.remove(proccesID);
            sendMessageToFinanceController(order.toString());
            return;
        }

        sendMessageToTickets((new Message(SERVER_NAME, "buy", String.valueOf(proccesID), String.valueOf(order.ticketId))).toString());
    }

    void afterBuyTicket(Integer proccesID, String response){
        String status = response.split(":")[0];
        String description = response.split(":")[1];

        Order order = inWorkOrders.get(proccesID);

        if ("error".equals(status)){
            order.status = "Order doesn't exist : " + description;
            sendMessageToFinanceController(order.toString());
            return;
        }

        order.setTicketDescription(description);
        order.setStatus("successfully");

        inWorkOrders.remove(proccesID);

        System.out.println(order);

        sendMessageToFinanceController(order.toString());

    }

    void sendMessageToTickets(String msg){
        template.convertAndSend("finance_tickets", msg);
    }

    void  sendMessageToUCS(String msg){
        template.convertAndSend("finance_UCS", msg);
    }

    void sendMessageToFinanceController(String msg){
        System.out.println("FINANCE_ORCHESTR_SEND_MSG");
        template.convertAndSend("orchestFinance", msg);
    }

}
