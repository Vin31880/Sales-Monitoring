package com.project.insight.service;

import com.project.insight.model.SalesDataRequest;
import org.springframework.stereotype.Service;

@Service
public class InsightService {

    public String analyse(SalesDataRequest request) {
        // LLM integration coming next — for now just echo back a summary
        return String.format(
                "Received sales data for site: %s with %d records. LLM analysis coming soon.",
                request.getSiteName(),
                request.getRecords() != null ? request.getRecords().size() : 0
        );
    }
}
