package com.project.service;

import com.project.model.SalesDataRequest;
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
