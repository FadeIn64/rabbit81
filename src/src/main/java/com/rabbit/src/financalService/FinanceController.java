package com.rabbit.src.financalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FinanceController {

    @Autowired
    FinanceOrchestr orchestr;

    void createOrder(String userToken, int ticketID){
        orchestr.createOrder(userToken, ticketID);
    }

    void addOrder(String obj){
        System.out.println(obj);
    }

}
