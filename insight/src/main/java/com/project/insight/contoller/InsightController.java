package com.project.insight.contoller;

import com.project.insight.model.AnalysisResponse;
import com.project.insight.model.SalesDataRequest;
import com.project.insight.service.InsightAIService;
import com.project.insight.service.InsightService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/insight")
@RequiredArgsConstructor
public class InsightController {

    private final InsightService insightService;
    private final InsightAIService insightAIService;

    @PostMapping("/analyse")
    public ResponseEntity<AnalysisResponse> analyse(@RequestBody SalesDataRequest request) {
        AnalysisResponse insight = insightAIService.analyse(request);
        return ResponseEntity.ok(insight);
    }
}
