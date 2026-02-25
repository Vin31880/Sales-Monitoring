package com.project.insight.service;

import com.project.insight.model.SalesDataRequest;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.ollama.OllamaChatModel;

import dev.langchain4j.model.output.Response;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class InsightAIService {

    private final ChatLanguageModel model;

    public InsightAIService() {
        this.model = OllamaChatModel.builder().
                baseUrl("http://localhost:11434").modelName("mistral").temperature(0.7)
                .timeout(Duration.ofMinutes(3))
                .build();
    }

    public String analyse(SalesDataRequest request) {
        String prompt = buildPrompt(request);
        Response<AiMessage> response = model.generate(UserMessage.from(prompt));
        return  response.content().text();
    }

    private String buildPrompt(SalesDataRequest request) {
        StringBuilder sb = new StringBuilder();
        sb.append("You are a sales analyst AI for a car rental company.\n");
        sb.append("Analyse the following daily sales data for site: ")
                .append(request.getSiteName())
                .append(" (ID: ").append(request.getSiteId()).append(")\n\n");

        sb.append("Sales Records:\n");
        request.getRecords().forEach(record -> {
            sb.append(String.format("- Date: %s | Revenue: €%.2f | Transactions: %d\n",
                    record.getDate(),
                    record.getRevenue(),
                    record.getTransactions()));
        });

        sb.append("\nProvide:\n");
        sb.append("1. A brief summary of overall performance\n");
        sb.append("2. Any anomalies or unusual patterns you detect\n");
        sb.append("3. A recommended action for the site manager\n");
        sb.append("\nBe concise and specific. Use the actual numbers in your response.");

        return sb.toString();
    }


}
