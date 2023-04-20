package com.rabbit.src;

import com.rabbit.src.financalService.FinanceOrchestr;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("sample")
public class SampleController {

    @Autowired
    AmqpTemplate template;

    @Autowired
    FinanceOrchestr orchestr;

    @GetMapping("/emit")
    @ResponseBody
    String queue1(){
//        System.out.println("Send");
//        //template.convertAndSend("finance_UCS", "verify_asdsd; xxx");
//        Message msg = new Message("sample", "verify", "asdsd", "xxx");
//        System.out.println(msg);
//        template.convertAndSend("finance_UCS", msg.toString());

        orchestr.createOrder("xxx", 2);

        return "send!!!";
    }
}
