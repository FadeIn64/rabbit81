package com.rabbit.src;

public class Message {
    private static final String SEPARATOR = ":::";

    public final String serverName;
    public final String methodName;
    public final String proccesID;
    public final String parameters;

    public Message(String serverName, String methodName, String proccesID, String parameters) {
        this(serverName+SEPARATOR+methodName+SEPARATOR+proccesID+SEPARATOR+ parameters);
    }

    public Message(String msg){
        String[] msgs = msg.split(SEPARATOR);
        if( msgs.length < 4) throw new IllegalArgumentException("Message not Correct");
        serverName = msgs[0];
        methodName = msgs[1];
        proccesID = msgs[2];
        parameters = msgs[3];
    }

    @Override
    public String toString() {
        return serverName+SEPARATOR+methodName+SEPARATOR+proccesID+SEPARATOR+ parameters;
    }
}
