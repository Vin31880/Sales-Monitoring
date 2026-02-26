package com.project.insight.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnalysisResponse {
    private String siteId;
    private String siteName;
    private boolean anomalyDetected;
    private String severity;        // LOW, MEDIUM, HIGH
    private String summary;
    private List<String> possibleCauses;
    private String recommendation;

}
