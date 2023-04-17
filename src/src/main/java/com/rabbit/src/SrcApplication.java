package com.rabbit.src;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(Config.class)
public class SrcApplication {

	public static void main(String[] args) {
		SpringApplication.run(SrcApplication.class, args);
		System.out.println("start");

//		ApplicationContext ctx = MyContext.context;
//
//		ctx.getBean("sample", SampleController.class).queue1();
//		ctx.getBean("sample", SampleController.class).queue1();
//		ctx.getBean("sample", SampleController.class).queue1();
	}

}
