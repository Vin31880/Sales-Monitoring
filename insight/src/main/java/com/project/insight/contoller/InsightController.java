package com.project.contoller;

import com.project.model.SalesDataRequest;
import com.project.service.InsightService;
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

    @PostMapping("/analyse")
    public ResponseEntity<String> analyse(@RequestBody SalesDataRequest request) {
        String insight = insightService.analyse(request);
        return ResponseEntity.ok(insight);
    }
}
