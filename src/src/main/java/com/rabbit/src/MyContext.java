package com.rabbit.src;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component
public class MyContext {

    public static ApplicationContext context = null;

    ApplicationContext ctx;

    public MyContext(ApplicationContext ctx) {
        this.ctx = ctx;
        context = ctx;
    }
}
