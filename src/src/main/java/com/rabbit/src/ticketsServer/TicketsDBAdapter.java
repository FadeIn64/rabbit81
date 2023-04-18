package com.rabbit.src.ticketsServer;

import com.rabbit.src.Adapter;
import org.springframework.stereotype.Component;

@Component
public class TicketsDBAdapter implements Adapter<Ticket, Integer> {

    public Ticket get(Integer id){
        return TicketsDB.TICKETS.get(id);
    }

    public void put(Ticket ticket){
        TicketsDB.TICKETS.put( TicketsDB.getID(), ticket);
    }

    @Override
    public boolean contentsObj(Integer id) {
        return TicketsDB.TICKETS.containsKey(id);
    }

    @Override
    public void deleteObj(Integer id) {
        TicketsDB.TICKETS.remove(id);
    }
}
