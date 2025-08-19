package com.myproj.chatapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ChatappApplication {

	public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(ChatappApplication.class, args);
    }

}
