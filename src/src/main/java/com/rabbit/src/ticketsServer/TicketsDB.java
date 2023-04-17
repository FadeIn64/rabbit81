package com.rabbit.src.ticketsServer;

import java.util.HashMap;
import java.util.Map;

public class TicketsDB {
    public final static Map<Integer, Ticket> TICKETS = new HashMap<>();
    private static int counter = 0;

    static int getID(){
        return counter++;
    }

    static {
        TICKETS.put(counter++, new Ticket("A1B2"));
        TICKETS.put(counter++, new Ticket("A2B2"));
        TICKETS.put(counter++, new Ticket("A3B2"));
        TICKETS.put(counter++, new Ticket("A4B2"));
    }
}
