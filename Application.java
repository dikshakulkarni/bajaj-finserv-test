package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.service.WebhookService;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private WebhookService webhookService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) {
        try {
            webhookService.executeFlow();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
