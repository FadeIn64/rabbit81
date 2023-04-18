package com.rabbit.src.userControllService;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    TokensAdapter adapter;

    @Autowired
    AmqpTemplate template;

    String verifyUser(String token){
        return String.valueOf(adapter.contentsObj(token));
    }

    void sendMSGToFinance(String msg){
        System.out.println("TICKET_SERVICE_SEND_MSG");
        template.convertAndSend("financeOrchestr", msg);
    }

}
