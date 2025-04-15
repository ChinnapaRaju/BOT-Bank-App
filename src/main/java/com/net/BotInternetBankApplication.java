package com.net;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BotInternetBankApplication {
	
//	public BeanNameViewResolver createViewResolver() {
//		BeanNameViewResolver resolver=new BeanNameViewResolver();
//		resolver.setOrder(Ordered.HIGHEST_PRECEDENCE);
//		return resolver;
//	}

	public static void main(String[] args) {
		SpringApplication.run(BotInternetBankApplication.class, args);
	}

}
