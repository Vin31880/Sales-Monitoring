package com.project.insight.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.insight.model.AnalysisResponse;
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
    private final ObjectMapper objectMapper;

    public InsightAIService() {
        this.model = OllamaChatModel.builder().
                baseUrl("http://localhost:11434").modelName("mistral").temperature(0.7)
                .timeout(Duration.ofMinutes(3))
                .build();
        this.objectMapper = new ObjectMapper();
    }

    public AnalysisResponse analyse(SalesDataRequest request) {
        String prompt = buildPrompt(request);
        Response<AiMessage> response = model.generate(UserMessage.from(prompt));
        String rawJson =  response.content().text().trim();
        try {
            AnalysisResponse analysisResponse = this.objectMapper.readValue(rawJson, AnalysisResponse.class);
            analysisResponse.setSiteId(request.getSiteId());
            analysisResponse.setSiteName(request.getSiteName());

            return analysisResponse;
        } catch (JsonProcessingException e) {
            return AnalysisResponse.builder()
                    .siteId(request.getSiteId())
                    .siteName(request.getSiteName())
                    .anomalyDetected(false)
                    .severity("UNKNOWN")
                    .summary(rawJson)
                    .recommendation("Manual review required")
                    .build();
        }
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
        sb.append("\nRespond ONLY with a valid JSON object — no explanation, no markdown, no extra text.\n");
        sb.append("\nRules for analysis:\n");
        sb.append("- If any single day revenue drops more than 20% vs the average, set anomalyDetected to true\n");
        sb.append("- severity: LOW if drop < 20%, MEDIUM if 20-35%, HIGH if > 35%\n");
        sb.append("\nRespond ONLY with a valid JSON object — no explanation, no markdown, no extra text.\n");
        sb.append("JSON format:\n");
        sb.append("""
                {
                  "anomalyDetected": true or false,
                  "severity": "LOW or MEDIUM or HIGH",
                  "summary": "one sentence summary of performance",
                  "possibleCauses": ["cause1", "cause2"],
                  "recommendation": "one clear action for the site manager"
                }
                """);

        return sb.toString();
    }


}
