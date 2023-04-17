package com.rabbit.src;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("sample")
public class SampleController {

    @Autowired
    AmqpTemplate template;

    @GetMapping("/emit")
    @ResponseBody
    String queue1(){
        System.out.println("Send");
        template.convertAndSend("queue1", "Hello world");
        return "send!!!";
    }
}
