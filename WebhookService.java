package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import org.springframework.core.io.ClassPathResource;

import java.util.*;

@Service
public class WebhookService {

    private final RestTemplate restTemplate = new RestTemplate();

    private static final String GENERATE_URL =
            "https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA";


    public void executeFlow() throws Exception {

        Map<String, String> request = new HashMap<>();
        request.put("name", "John Doe");
        request.put("regNo", "REG12348");   // change to yours
        request.put("email", "john@example.com");

        ResponseEntity<Map> response =
                restTemplate.postForEntity(GENERATE_URL, request, Map.class);

        String webhookUrl = (String) response.getBody().get("webhook");
        String token = (String) response.getBody().get("accessToken");

        String finalQuery = solveSql();

        submitSolution(webhookUrl, token, finalQuery);
    }


    private String solveSql() throws Exception {
        ClassPathResource resource = new ClassPathResource("query.sql");
        return new String(resource.getInputStream().readAllBytes());
    }


    private void submitSolution(String url, String token, String query) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", token);

        Map<String, String> body = new HashMap<>();
        body.put("finalQuery", query);

        HttpEntity<Map<String, String>> entity =
                new HttpEntity<>(body, headers);

        restTemplate.postForEntity(url, entity, String.class);

        System.out.println("✅ Submitted successfully!");
    }
}
