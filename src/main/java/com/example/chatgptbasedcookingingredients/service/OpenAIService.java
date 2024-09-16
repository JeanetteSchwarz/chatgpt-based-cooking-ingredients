package com.example.chatgptbasedcookingingredients.service;


import com.example.chatgptbasedcookingingredients.model.OpenAIRequest;
import com.example.chatgptbasedcookingingredients.model.OpenAiMessage;
import com.example.chatgptbasedcookingingredients.model.OpenAiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class OpenAIService {

    private final RestClient client;

    public OpenAIService(@Value("${BASE_URL}") String baseUrl,
                         @Value("${AUTH_KEY}") String key) {
        client = RestClient.builder()
                .defaultHeader("Authorization", "Bearer " + key)
                .baseUrl(baseUrl)
                .build();
    }

    public String categorizeIngredient(String question) {
        String prompt = "Please categorize the following ingredient in on of these categories: vegan, vegetarian or regular";
        OpenAIRequest request = new OpenAIRequest("gpt-4o-mini",
                List.of(new OpenAiMessage("user", prompt + question)),
                0.2);

        OpenAiResponse response = client.post()
                .contentType(MediaType.APPLICATION_JSON)
                .body(request)
                .retrieve()
                .body(OpenAiResponse.class);

        return response.getAnswer();
    }
}
