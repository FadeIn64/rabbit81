package com.rabbit.src.userControllService;

import com.rabbit.src.MSGConvertor;
import com.rabbit.src.Message;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@EnableRabbit
@Component
public class UCSListener {

    private static final String SERVER_NAME = "ucs";

    @Autowired
    UsersController controller;

    @RabbitListener(queues = "finance_UCS")
    void listener(String msg){

        System.out.println("UCS_CHECK");
        System.out.println(msg);

        Message message = new Message(msg);
        //List<String> attributes = MSGConvertor.getAttributes(msg);

        System.out.println(message);
        Message res;

        if (Objects.equals(message.methodName, "verify")){
            res = new Message(SERVER_NAME, "response", message.proccesID
                    , controller.verifyUser(message.parameters));
        }
        else {
            res = new Message(SERVER_NAME, "response", message.proccesID, "error:CalledMethodDon'tExist");
        }
        controller.sendMSGToFinance(res.toString());
    }


}
