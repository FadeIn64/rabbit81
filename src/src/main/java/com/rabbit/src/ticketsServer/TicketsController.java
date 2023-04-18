package com.rabbit.src.ticketsServer;

import com.rabbit.src.Adapter;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tickets")
public class TicketsController {

    @Autowired
    private Adapter<Ticket, Integer> adapter;

    @Autowired
    AmqpTemplate template;

    String tryBuyTicket(Integer id){
        if (!adapter.contentsObj(id)) return "error:CannotFindTicketWithID";
        Ticket ticket = adapter.get(id);
        if (ticket.isTicketInUsage) return "error:TicketInUsage";
        ticket.isTicketInUsage = true;
        return "successfully:"+id;
    }

    String buyTicket(Integer id){
        if (!adapter.contentsObj(id)) return "error:CannotFindTicketWithID";
        Ticket ticket = adapter.get(id);
        String res = ticket.description;
        adapter.deleteObj(id);
        return "successfully:"+res;
    }

    void rollbackTicket(Integer id){
        Ticket ticket = adapter.get(id);
        if (ticket == null) return;
        ticket.isTicketInUsage = false;
    }

    void sendMSGToFinance(String msg){
        System.out.println("TICKET_SERVICE_SEND_MSG");
        template.convertAndSend("financeOrchestr", msg);
    }
}
