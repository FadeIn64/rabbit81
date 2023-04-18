package com.rabbit.src.userControllService;

import com.rabbit.src.MSGConvertor;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@EnableRabbit
@Component
public class UCSListener {

    @Autowired
    UsersController controller;

    @RabbitListener(queues = "finance_UCS")
    void listener(String msg){

        List<String> attributes = MSGConvertor.getAttributes(msg);
        String res;

        if (Objects.equals(attributes.get(0), "verify")){
            res = MSGConvertor.createReturnMSG( attributes.get(1)
                    , controller.verifyUser(attributes.get(2)));
        }
        else {
            res = MSGConvertor.createReturnMSG(attributes.get(1), "error:CalledMethodDon'tExist");
        }
        controller.sendMSGToFinance(res);
    }


}
