package com.rabbit.src;

import java.util.ArrayList;
import java.util.List;

public class MSGConvertor {

    private static final String METHOD_SEPARATOR = "_";
    private static final String PID_SEPARATOR = "; ";
    public static List<String> getAttributes(String msg){
        List<String> res = new ArrayList<>();
        res.add(msg.split(METHOD_SEPARATOR)[0]); //called method's name
        res.add(msg.split(METHOD_SEPARATOR)[1].split(PID_SEPARATOR)[0]); //Producer PID
        res.add(msg.split(METHOD_SEPARATOR)[1].split(PID_SEPARATOR)[1]); //Entity ID
        return res;
    }

    public static String createReturnMSG(String producerID, String response){
        return producerID + METHOD_SEPARATOR + response;
    }

    public static String createRequestMSG(String methodName, String producerID, String parameters){
        return methodName+METHOD_SEPARATOR+producerID+PID_SEPARATOR+parameters;
    }
}
