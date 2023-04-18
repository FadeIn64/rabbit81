package com.rabbit.src.ticketsServer;

public class Ticket {
    String description;
    boolean isTicketInUsage = false;

    public Ticket(String description) {
        this.description = description;
    }
}
