package com.rabbit.src.ticketsServer;

import com.rabbit.src.Adapter;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tickets")
public class TicketsController {

    private Adapter<Ticket, Integer> adapter = new TicketsDBAdapter();

    String buyTicket(Integer id){
        if (!adapter.contentsObj(id)) return "error";
        String res = adapter.get(id).description;
        return res;
    }
}