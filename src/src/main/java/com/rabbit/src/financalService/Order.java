package com.rabbit.src.financalService;

import lombok.Getter;
import lombok.ToString;

@ToString
public class Order {
    public final String userToken;

    public final int ticketId;

    @Getter
    private String ticketDescription;

    @Getter
    String status;

    public Order(String userToken, int ticketId, String ticketDescription) {
        this.userToken = userToken;
        this.ticketId = ticketId;
        this.ticketDescription = ticketDescription;
        this.status = "inWork";
    }

    void setTicketDescription(String ticketDescription){
        this.ticketDescription = ticketDescription;
    }

    void setStatus(String status){
        this.status = status;
    }
}
