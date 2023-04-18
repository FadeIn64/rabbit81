package com.rabbit.src;

import java.util.ArrayList;
import java.util.List;

public class MSGConvertor {
    public static List<String> getAttributes(String msg){
        List<String> res = new ArrayList<>();
        res.add(msg.split("_")[0]); //called method's name
        res.add(msg.split("_")[1].split("; ")[0]); //Producer PID
        res.add(msg.split("_")[1].split("; ")[1]); //Entity ID
        return res;
    }

    public static String createReturnMSG(String producerID, String response){
        return producerID + "; " + response;
    }
}
